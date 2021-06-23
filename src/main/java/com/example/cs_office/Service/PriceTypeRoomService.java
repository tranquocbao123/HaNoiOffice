package com.example.cs_office.Service;

import com.example.cs_office.Model.PriceTypeRoom;
import com.example.cs_office.Model.Room;
import com.example.cs_office.Repository.PriceTypeRoomRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PriceTypeRoomService {

    @Autowired
    private final PriceTypeRoomRepository priceTypeRoomRepository;

    public PriceTypeRoomService(PriceTypeRoomRepository priceTypeRoomRepository) {
        this.priceTypeRoomRepository = priceTypeRoomRepository;
    }

    public List<PriceTypeRoom> getPriceTypeRoom() {
        return priceTypeRoomRepository.findAll();
    }

    public List<PriceTypeRoom> getPriceTypeRoomByStatus(boolean status) {

        return priceTypeRoomRepository.findPriceTypeRoomByStatus(status);
    }

    public Optional<PriceTypeRoom> getPriceTypeRoomById(int priceTypeRoomId) {
        Optional<PriceTypeRoom> priceTypeRoom = priceTypeRoomRepository.findById(priceTypeRoomId);
        return priceTypeRoom;
    }

    public void addNewPriceTypeRoom(PriceTypeRoom priceTypeRoom) {
        Optional<PriceTypeRoom> priceTypeRoomOptional =
                priceTypeRoomRepository.findPriceTypeRoomById(priceTypeRoom.getId());
        if (priceTypeRoomOptional.isPresent()) {
            throw new IllegalStateException("Id taken");
        }
        priceTypeRoomRepository.save(priceTypeRoom);
    }

    public void deletePriceTypeRoom(int priceTypeRoomId) {
        boolean exists = priceTypeRoomRepository.existsById(priceTypeRoomId);
        if (!exists) {
            throw new IllegalStateException("price type room with id " + priceTypeRoomId + " does not exists");
        }
        priceTypeRoomRepository.deleteById(priceTypeRoomId);
    }

    public PriceTypeRoom updatePriceTypeRoom(PriceTypeRoom priceTypeRoom, int priceTypeRoomId){
        PriceTypeRoom priceTypeRoom1 = this.priceTypeRoomRepository.getOne(priceTypeRoomId);
        BeanUtils.copyProperties(priceTypeRoom,priceTypeRoom1);
        return priceTypeRoomRepository.saveAndFlush(priceTypeRoom1);
    }
}
