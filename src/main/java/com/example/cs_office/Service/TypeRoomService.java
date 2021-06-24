package com.example.cs_office.Service;

import com.example.cs_office.Model.Evaluate;
import com.example.cs_office.Model.Staff;
import com.example.cs_office.Model.TypeRoom;
import com.example.cs_office.Repository.TypeRoomRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TypeRoomService {
    @Autowired
    private final TypeRoomRepository typeRoomRepository;

    public TypeRoomService(TypeRoomRepository typeRoomRepository) {
        this.typeRoomRepository = typeRoomRepository;
    }


    public List<TypeRoom> getTypeRoom() {
        return typeRoomRepository.findAll();
    }

    public List<TypeRoom> getTypeRoomByStatus(boolean status) {

        return typeRoomRepository.findTypeRoomByStatus(status);
    }

    public Optional<TypeRoom> getTypeRoomById(int typeRoomId) {
        Optional<TypeRoom> typeRoom = typeRoomRepository.findById(typeRoomId);
        return typeRoom;
    }

    public void addNewTypeRoom(TypeRoom typeRoom) {
        Optional<TypeRoom> typeRoomOptional =
                typeRoomRepository.findTypeRoomById(typeRoom.getId());
        if (typeRoomOptional.isPresent()) {
            throw new IllegalStateException("Id taken");
        }
        typeRoomRepository.save(typeRoom);
    }

    public List<TypeRoom> getTypeRoomByName(String typeRoomName) {
        List<TypeRoom> typeRooms = typeRoomRepository.findTypeRoomByName(typeRoomName);
        return typeRooms;
    }

    public void deleteTypeRoom(int typeRoomId) {
        boolean exists = typeRoomRepository.existsById(typeRoomId);
        if (!exists) {
            throw new IllegalStateException("type room with id " + typeRoomId + " does not exists");
        }
        typeRoomRepository.deleteById(typeRoomId);
    }

    @Transactional
    public TypeRoom updateTyperoom(TypeRoom typeRoom, int typeroomId){
        TypeRoom typeRoom1=this.typeRoomRepository.getOne(typeroomId);
        BeanUtils.copyProperties(typeRoom,typeRoom1);
        return typeRoomRepository.saveAndFlush(typeRoom1);
    }
}
