package com.tobeto.ChatterBoxBackend.repositories;

import com.tobeto.ChatterBoxBackend.entities.concretes.Message;
import com.tobeto.ChatterBoxBackend.services.dtos.message.response.GetAllMessagesResponse;
import com.tobeto.ChatterBoxBackend.services.dtos.user.response.GetAllUsersResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    //  JPQL Method
    @Query("select new com.tobeto.ChatterBoxBackend.services.dtos.message.response.GetAllMessagesResponse(" +
            "m.id, m.content, " +
            "new com.tobeto.ChatterBoxBackend.services.dtos.user.response.GetUserByIdResponse(s.id, s.userName, s.email, s.firstname, s.lastname, s.image), " +
            "new com.tobeto.ChatterBoxBackend.services.dtos.user.response.GetUserByIdResponse(r.id, r.userName, r.email, r.firstname, r.lastname, r.image), " +
            "m.isRead, m.createdDate) " +
            "from Message m " +
            "join m.sender s " +
            "join m.recipient r " +
            "where m.recipient.id = :userId or m.sender.id = :userId")
    List<GetAllMessagesResponse> getAllMessages(int userId);

    @Query("select new com.tobeto.ChatterBoxBackend.services.dtos.message.response.GetAllMessagesResponse(" +
            "m.id, m.content, " +
            "new com.tobeto.ChatterBoxBackend.services.dtos.user.response.GetUserByIdResponse(s.id, s.userName, s.email, s.firstname, s.lastname, s.image), " +
            "new com.tobeto.ChatterBoxBackend.services.dtos.user.response.GetUserByIdResponse(r.id, r.userName, r.email, r.firstname, r.lastname, r.image), " +
            "m.isRead, m.createdDate) " +
            "from Message m " +
            "join m.sender s " +
            "join m.recipient r " +
            "where (m.recipient.id = :userId and m.sender.id = :friendId) or (m.recipient.id = :friendId and m.sender.id = :userId)")
    List<GetAllMessagesResponse> getAllMessagesByFriends(int userId, int friendId);

    @Query("select distinct new com.tobeto.ChatterBoxBackend.services.dtos.user.response.GetAllUsersResponse(" +
            "u.id, u.userName, u.email, u.firstname, u.lastname, u.image) " +
            "from Message m " +
            "join m.sender u " +
            "where m.recipient.id = :userId " +
            "union " +
            "select distinct new com.tobeto.ChatterBoxBackend.services.dtos.user.response.GetAllUsersResponse(" +
            "u.id, u.userName, u.email, u.firstname, u.lastname, u.image) " +
            "from Message m " +
            "join m.recipient u " +
            "where m.sender.id = :userId")
    List<GetAllUsersResponse> getUsersInvolvedWithUser(@Param("userId") int userId);

    //List<GetAllMessagesResponse> getUnreadMessages(@Param("userId") int userId);

    // Find unread messages by recipient id

    @Query("select new com.tobeto.ChatterBoxBackend.services.dtos.message.response.GetAllMessagesResponse(" +
            "m.id, m.content, " +
            "new com.tobeto.ChatterBoxBackend.services.dtos.user.response.GetUserByIdResponse(s.id, s.userName, s.email, s.firstname, s.lastname, s.image), " +
            "new com.tobeto.ChatterBoxBackend.services.dtos.user.response.GetUserByIdResponse(r.id, r.userName, r.email, r.firstname, r.lastname, r.image), " +
            "m.isRead, m.createdDate) " +
            "from Message m " +
            "join m.sender s " +
            "join m.recipient r " +
            "where ((m.recipient.id = :userId and m.sender.id = :friendId) ) and m.isRead = false")
    List<GetAllMessagesResponse> getUnreadMessages(@Param("userId") int userId, @Param("friendId") Integer friendId);
//or (m.recipient.id = :friendId and m.sender.id = :userId)


    // Find read messages by recipient id
    @Query("select new com.tobeto.ChatterBoxBackend.services.dtos.message.response.GetAllMessagesResponse(" +
            "m.id, m.content, " +
            "new com.tobeto.ChatterBoxBackend.services.dtos.user.response.GetUserByIdResponse(s.id, s.userName, s.email, s.firstname, s.lastname, s.image), " +
            "new com.tobeto.ChatterBoxBackend.services.dtos.user.response.GetUserByIdResponse(r.id, r.userName, r.email, r.firstname, r.lastname, r.image), " +
            "m.isRead, m.createdDate) " +
            "from Message m " +
            "join m.sender s " +
            "join m.recipient r " +
            "where (m.recipient.id = :userId and m.sender.id = :friendId) or (m.recipient.id = :friendId and m.sender.id = :userId) and m.isRead = true")
    List<GetAllMessagesResponse> getReadMessages(@Param("userId") int userId, @Param("friendId") int friendId);

    @Query("SELECT m.sender.id AS senderId, COUNT(m) AS messageCount " +
            "FROM Message m " +
            "WHERE m.recipient.id = :userId AND m.isRead = false " +
            "GROUP BY m.sender.id")
    List<Object[]> findUnreadMessageCountsByUserId(@Param("userId") int userId);

}
