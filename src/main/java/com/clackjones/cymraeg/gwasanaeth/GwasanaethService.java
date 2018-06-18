package com.clackjones.cymraeg.gwasanaeth;

import com.clackjones.cymraeg.geolocation.GeolocationFinder;
import com.clackjones.cymraeg.gwasanaeth.web.GwasanaethSearchCriteria;
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GlobalCoordinates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.NoPermissionException;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class GwasanaethService {
    final static Logger logger = LoggerFactory.getLogger(GwasanaethService.class);

    private SylwToSylwEntityMapper sylwToEntity;
    private SylwEntityToSylwMapper entityToSylw;
    private SylwDao sylwDao;
    private GwasanaethDao gwasanaethDao;
    private GwasanaethToGwasanaethEntityMapper gwasanaethToEntity;
    private GwasanaethEntityToGwasanaethMapper entityToGwasanaeth;
    private GeolocationFinder geolocationFinder;

    @Autowired
    public GwasanaethService(
            SylwToSylwEntityMapper sylwToEntity,
            SylwEntityToSylwMapper entityToSylw,
            SylwDao sylwDao,
            GwasanaethDao gwasanaethDao,
            GwasanaethToGwasanaethEntityMapper gwasanaethToEntity,
            GwasanaethEntityToGwasanaethMapper entityToGwasanaeth,
            GeolocationFinder geolocationFinder
    ) {
        this.sylwToEntity = sylwToEntity;
        this.entityToSylw = entityToSylw;
        this.sylwDao = sylwDao;
        this.gwasanaethDao = gwasanaethDao;
        this.gwasanaethToEntity = gwasanaethToEntity;
        this.entityToGwasanaeth = entityToGwasanaeth;
        this.geolocationFinder = geolocationFinder;
    }

    @Transactional
    public Gwasanaeth findById(Long id) {

        GwasanaethEntity gwasanaethEntity = gwasanaethDao.findById(id);

        return entityToGwasanaeth.map(gwasanaethEntity);
    }

    @Transactional
    public List<GwasanaethDistanceResult> searchForServices(GwasanaethSearchCriteria searchCriteria) {
        Collection<GwasanaethEntity> result = gwasanaethDao.findByCategoryAndLanguage(searchCriteria.getCategoryId(), searchCriteria.getLanguage());
        return result.stream()
                .filter(gwasEntity -> gwasEntity.getLongitude() != null && gwasEntity.getLatitude() != null)
                .map(entityToGwasanaeth::map)
                .map(gwasanaeth -> new GwasanaethDistanceResult(calcGeoDistanceKm(searchCriteria, gwasanaeth), gwasanaeth))
                .collect(Collectors.toList());
    }

    private BigDecimal calcGeoDistanceKm(GwasanaethSearchCriteria searchCriteria, Gwasanaeth gwasanaeth) {
        return calcGeoDistanceKm(searchCriteria.getLatitude(), searchCriteria.getLongitude(),
                gwasanaeth.getLatitude(), gwasanaeth.getLongitude());
    }

    private BigDecimal calcGeoDistanceKm(BigDecimal thisLat, BigDecimal thisLng, BigDecimal thatLat, BigDecimal thatLng) {
        GeodeticCalculator geoCalc = new GeodeticCalculator();

        GlobalCoordinates thisCoord = new GlobalCoordinates(thisLat.doubleValue(), thisLng.doubleValue());
        GlobalCoordinates thatCoord = new GlobalCoordinates(thatLat.doubleValue(), thatLng.doubleValue());

        return BigDecimal.valueOf(geoCalc.calculateGeodeticCurve(Ellipsoid.WGS84, thisCoord, thatCoord).getEllipsoidalDistance())
                .divide(BigDecimal.valueOf(1000));
    }

    @Transactional
    public List<Gwasanaeth> freeSearchByNameAndCity(String searchTerm, String dinasToFilter, String categoriToFilter) {
        logger.info("Searching for Services by name and city with search term {}", searchTerm);
        logger.info("Searching for Services filtered by city: {} and categori: {}", dinasToFilter, categoriToFilter);

        Collection<GwasanaethEntity> result;
        if (searchTerm != null && !searchTerm.isEmpty()) {
            result = gwasanaethDao.findByCityOrServiceName(searchTerm);
        } else {
            result = gwasanaethDao.findAll();
        }
        Stream<Gwasanaeth> gwasanaethauStream = result.stream()
                .map(entityToGwasanaeth::map)
                .filter(g -> matchesDinas(g, dinasToFilter))
                .filter(g -> matchesCategori(g, categoriToFilter));

        Stream<Gwasanaeth> sortedResults = gwasanaethauStream.sorted(Comparator.comparing(Gwasanaeth::getCyfeiriadDinas, String::compareToIgnoreCase)
                .thenComparing(Comparator.comparing(Gwasanaeth::getEnw, String::compareToIgnoreCase)));

        return sortedResults.collect(Collectors.toList());
    }

    private boolean matchesDinas(Gwasanaeth gwasanaeth, String dinas) {
        return dinas == null || gwasanaeth.getCyfeiriadDinas().equals(dinas);
    }

    private boolean matchesCategori(Gwasanaeth gwasanaeth, String categori) {
        return categori == null || gwasanaeth.getCategori().getCategori().equals(categori);
    }

    /**
     * @return the ID of the persisted Gwasanaeth
     */
    @Transactional
    public Long saveGwasanaeth(Gwasanaeth gwasanaeth, String username) {
        logger.info("Saving new service for user {} username", username);
        // find latitude and longitude
        geolocationFinder.findLocation(gwasanaeth).ifPresent(ltlng -> {
                logger.debug("Saving lat lng coordinates to service for user {} username", username);
                gwasanaeth.setLongitude(BigDecimal.valueOf(ltlng.lng));
                gwasanaeth.setLatitude(BigDecimal.valueOf(ltlng.lat));
        });

        GwasanaethEntity gwasanaethEntity = gwasanaethToEntity.map(gwasanaeth);
        gwasanaethEntity.setOwnerUsername(username);
        gwasanaethDao.persist(gwasanaethEntity);

        return gwasanaethEntity.getId();
    }

    @Transactional
    public void addSylwForGwasanaethWithId(long gwasanaethId, Sylw sylw) {
        logger.info("Adding comment '{}' to Service number {}", gwasanaethId, sylw.getSylw());
        GwasanaethEntity gwasanaethEntity = gwasanaethDao.findById(gwasanaethId);

        if (gwasanaethEntity == null) {
            logger.warn("Could not add comment because service number {} doesn't exist", gwasanaethId);
            // TODO add error - gwasanaeth doesn't exist
            throw new NullPointerException();
        }

        // TODO add validator for sylw
        SylwEntity sylwEntity = sylwToEntity.map(sylw);
        sylwEntity.setGwasanaeth(gwasanaethEntity);
        sylwDao.persist(sylwEntity);

        gwasanaethEntity.getSylwadau().add(sylwEntity);
    }

    @Transactional
    public void updateGwasanaeth(Gwasanaeth gwasanaeth, String name) throws GwasanaethNotFound, NoPermissionException {
        logger.info("Updating details for Service number {}", gwasanaeth.getId());
        if (gwasanaeth.getId() == null) {
            throw new NullPointerException("Gwasanaeth ID not provided");
        }

        GwasanaethEntity entity = gwasanaethDao.findById(gwasanaeth.getId());
        if (entity  == null) {
            throw new GwasanaethNotFound(String.format("Couldn't find Gwasanaeth with id %d\n", gwasanaeth.getId()));
        }

        if (!entity.getOwnerUsername().equals(name)) {
            throw new NoPermissionException(
                    String.format("User %s doesn't have permission to modify this gwasanaeth",name));
        }

        geolocationFinder.findLocation(gwasanaeth).ifPresent(ltlng -> {
            logger.debug("Updating lat lng coordinates for service for user {} username", name);
            gwasanaeth.setLongitude(BigDecimal.valueOf(ltlng.lng));
            gwasanaeth.setLatitude(BigDecimal.valueOf(ltlng.lat));
        });

        gwasanaethToEntity.map(gwasanaeth, entity);
        gwasanaethDao.merge(entity);
    }

    public List<String> calculateAZServiceNames() {
        return gwasanaethDao.findUniqueFirstCharacters();
    }
}
