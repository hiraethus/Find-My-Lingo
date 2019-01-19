package com.clackjones.cymraeg.gwasanaeth;

import com.clackjones.cymraeg.image.ImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class CategoriManager {

    private ImageRepo imageRepo;
    private CategoriDao categoriDao;
    private CategoriEntityToCategoriMapper entityToCategori;

    @Autowired
    public CategoriManager(CategoriDao categoriDao,
                           CategoriEntityToCategoriMapper categoriEntityToCategoriMapper,
                           ImageRepo imageRepo) {
        this.categoriDao = categoriDao;
        this.entityToCategori = categoriEntityToCategoriMapper;
        this.imageRepo = imageRepo;
    }

    public Categori findById(Long id) {
        CategoriEntity entity = categoriDao.findById(id);

        if (entity == null) {
            return null;
        }

        return entityToCategori.map(entity);
    }

    private Function<Categori, Categori> setCategoriImg = c -> {
        List<File> imagesForCategori = imageRepo.getImagesForCategori(c.getId(), null);
        if (imagesForCategori != null && !imagesForCategori.isEmpty()) {
            Path firstImg = imagesForCategori.get(0).toPath();
            Path trimmedPathFirstImg = firstImg.subpath(3, firstImg.getNameCount());
            c.setCategoriImg(trimmedPathFirstImg.toString());
        }

        return c;
    };

    public List<Categori> findAll() {
        Collection<CategoriEntity> categoriEntities = categoriDao.findAll();
        return categoriEntities.stream()
                .map(entity -> entityToCategori.map(entity))
                .map(setCategoriImg)
                .collect(Collectors.toList());
    }

    @Transactional
    public void saveCategori(String categoriName) {
        CategoriEntity categoriEntity = new CategoriEntity();
        categoriEntity.setCategori(categoriName);

        categoriDao.persist(categoriEntity);
    }

    @Transactional
    public void removeCategori(String categoriName) {
        Predicate<CategoriEntity> matchesCategoryName =
                c -> c.getCategori().equals(categoriName);

        categoriDao.findAll().stream()
                .filter(matchesCategoryName)
                .findFirst()
                .ifPresent(categoriDao::remove);
    }
}
