package com.clackjones.cymraeg.gwasanaeth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriManager {

    @Autowired
    private CategoriDao categoriDao;

    @Autowired
    private CategoriEntityToCategoriMapper entityToCategori;

    public Categori findById(Long id) {
        CategoriEntity entity = categoriDao.findById(id);

        if (entity == null) {
            return null;
        }

        return entityToCategori.map(entity);
    }

    public List<Categori> findAll() {
        Collection<CategoriEntity> categoriEntities = categoriDao.findAll();
        return categoriEntities.stream()
                .map(entity -> entityToCategori.map(entity))
                .collect(Collectors.toList());
    }

    @Transactional
    public void saveCategori(String categoriName) {
        CategoriEntity categoriEntity = new CategoriEntity();
        categoriEntity.setCategori(categoriName);

        categoriDao.persist(categoriEntity);
    }
}
