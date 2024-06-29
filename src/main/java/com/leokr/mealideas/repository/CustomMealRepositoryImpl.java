package com.leokr.mealideas.repository;

import com.leokr.mealideas.model.Meal;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public class CustomMealRepositoryImpl implements CustomMealRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Meal> GetMealsBeforeLastUse(Date lastUse) {
        Query query = entityManager.createQuery("select m from Meal m where m.date_lastuse < ?1 or m.date_lastuse is null");

        List meals = query.setParameter(1, lastUse, TemporalType.DATE).unwrap(Query.class).getResultList();

        return meals;
    }

    @Override
    public List<Meal> GetMealsBeforeLastUseAndType(Date lastUse, long type) {
        Query query = entityManager.createQuery("select m from Meal m where (m.date_lastuse < ?1 or m.date_lastuse is null) and m.type = ?2");

        List meals = query.setParameter(1, lastUse, TemporalType.TIMESTAMP).setParameter(2,type).unwrap(Query.class).getResultList();

        return meals;
    }

    @Override
    @Transactional
    public int UpdateLastUseForMeals(List<Long> ids) {
        Query query = entityManager.createQuery("update Meal set date_lastuse = ?1, counter = counter + 1 where id in ?2");

        entityManager.joinTransaction();

        int changed = query.setParameter(1, new Date(), TemporalType.TIMESTAMP).setParameter(2,ids).executeUpdate();
        return changed;
    }
}
