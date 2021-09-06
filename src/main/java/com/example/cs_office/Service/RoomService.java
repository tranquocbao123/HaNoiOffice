package com.example.cs_office.Service;

import com.example.cs_office.Model.Dto.CheckRoom;
import com.example.cs_office.Model.Entity.Branch;
import com.example.cs_office.Model.Entity.Room;
import com.example.cs_office.Model.Entity.TypeRoom;
import com.example.cs_office.Repository.BranchRepotitory;
import com.example.cs_office.Repository.RoomRepository;
import com.example.cs_office.Repository.TypeRoomRepository;
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

    @Autowired
    private TypeRoomRepository typeRoomRepository;

    @Autowired
    private BranchRepotitory branchRepotitory;

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

    public List<Room> getListRoomByTypeRoomAndBranch(String idTypeRoom, String idBranch) {
        return roomRepository.getListRoomByTypeRoomAndBranch(idTypeRoom, idBranch);
    }

    public Room MapperRoom(com.example.cs_office.Model.Dto.Room roomDto) {
        Room room = new Room();
        room.setId(roomDto.getId());
        room.setCodeRoom(roomDto.getCodeRoom());
        room.setName(roomDto.getName());
        room.setSoChoNgoi(roomDto.getSoChoNgoi());
        room.setDescription(roomDto.getDescription());
        Optional<TypeRoom> typeRoom = typeRoomRepository.findTypeRoomById(roomDto.getTypeRoom());
        room.setTypeRoom(typeRoom.get());
        Optional<Branch> branch = branchRepotitory.findBranchById(roomDto.getBranch());
        room.setBranch1(branch.get());
        return room;
    }
    public void addNewRoom(com.example.cs_office.Model.Dto.Room room) {
        roomRepository.save(MapperRoom(room));
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
    public Room updateRoom(com.example.cs_office.Model.Dto.Room roomDto, int roomId) {
        Room room = MapperRoom(roomDto);
        Room room1 = this.roomRepository.getOne(roomId);
        BeanUtils.copyProperties(room, room1);
        return roomRepository.saveAndFlush(room1);
    }

}
