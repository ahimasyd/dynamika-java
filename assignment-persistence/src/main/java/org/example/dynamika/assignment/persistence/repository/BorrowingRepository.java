package org.example.dynamika.assignment.persistence.repository;

import org.example.dynamika.assignment.persistence.entity.Borrowing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, Long>, JpaSpecificationExecutor<Borrowing> {

    @Query("select b" +
            " from Borrowing b" +
            "   join fetch b.book" +
            "   join fetch b.client" +
            " where b.endDate is null")
    List<Borrowing> findAllOngoingFetchAll();

    @Query("select b" +
            " from Borrowing b" +
            " where b.book.id = :bookId" +
            "   and b.client.id = :clientId" +
            "   and b.endDate is null")
    Optional<Borrowing> findOngoingByBookIdAndClientId(@Param("bookId") Long bookId, @Param("clientId") Long clientId);

}
