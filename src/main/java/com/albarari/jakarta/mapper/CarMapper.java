package com.albarari.jakarta.mapper;

import com.albarari.jakarta.dto.CarDto;
import com.albarari.jakarta.entity.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.bson.types.ObjectId;

@Mapper(imports = ObjectId.class)
public interface CarMapper {

    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    @Mapping(target = "id", expression = "java(car.getId() != null ? car.getId().toHexString() : null)")
    CarDto carToCarDto(Car car);

    @Mapping(target = "id", expression = "java(carDto.getId() != null ? new ObjectId(carDto.getId()) : null)")
    Car carDtoToCar(CarDto carDto);
}