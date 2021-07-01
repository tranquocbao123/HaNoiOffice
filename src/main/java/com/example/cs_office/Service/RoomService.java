package com.example.cs_office.Service;

import com.example.cs_office.Model.Room;
import com.example.cs_office.Repository.RoomRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    @Autowired
    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getRoom() {

        return roomRepository.findAll();
    }

    public List<Room> getRoomByStatus(boolean status) {

        return roomRepository.findRoomByStatus(status);
    }

    public Optional<Room> getRoomById(int roomId) {
        Optional<Room> room = roomRepository.findById(roomId);
        return room;
    }

    public List<Room> getRoomByName(String roomName) {
        List<Room> room = roomRepository.findRoomByName(roomName);
        return room;
    }

    public void addNewRoom(Room room) {
        Optional<Room> roomOptional =
                roomRepository.findRoomById(room.getId());
        if (roomOptional.isPresent()) {
            throw new IllegalStateException("Id taken");
        }
        roomRepository.save(room);
    }

    public void deleteRoom(int roomId) {
        boolean exists = roomRepository.existsById(roomId);
        if (!exists) {
            throw new IllegalStateException("room with id " + roomId + " does not exists");
        }
        roomRepository.deleteById(roomId);
    }

    @Transactional
    public Room updateRoomStatus(Room room) {
        room.setStatus(false);
        return roomRepository.save(room);
    }

    @Transactional
    public Room updateRoomBlack(Room room) {
        room.setStatus(true);
        return roomRepository.save(room);
    }

    @Transactional
    public Room updateRoom(Room room, int roomId){
        Room room1 = this.roomRepository.getOne(roomId);
        BeanUtils.copyProperties(room,room1);
        return roomRepository.saveAndFlush(room1);
    }
}
