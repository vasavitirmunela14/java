package com.cba.book.repository;

import com.cba.book.model.BookCatalogue;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookCatalogueRepositoryImpl implements BookCatalogueRepositoryCustom{

   @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<BookCatalogue> findBooksByTitleAndAuthor(String title, String author) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<BookCatalogue> cq = cb.createQuery(BookCatalogue.class);
        Root<BookCatalogue> book = cq.from(BookCatalogue.class);
        List<Predicate> predicates = new ArrayList<>();
        if (author != null) {
            predicates.add(cb.like(book.get("author"), "%" + author + "%"));
        }
        if (title != null) {
            predicates.add(cb.like(book.get("title"), "%" + title + "%"));
        }

        Predicate predicateForGrade = cb.and(predicates.toArray(new Predicate[0]));
        cq.where(predicateForGrade);
        return entityManager.createQuery(cq).getResultList();

    }


}
