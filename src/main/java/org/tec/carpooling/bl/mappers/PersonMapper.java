package org.tec.carpooling.bl.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.tec.carpooling.da.entities.PersonEntity;
import org.tec.carpooling.da.entities.PersonalUserEntity;
import org.tec.carpooling.bl.dto.PersonDTO;

@Mapper
public interface PersonMapper {
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);
    @Mapping(source = "person.id", target = "id")
    @Mapping(source = "person.firstName", target = "firstName")
    @Mapping(source = "person.firstSurname", target = "firstSurname")
    @Mapping(source = "personalUser.username", target = "username")
    PersonDTO toUserDTO(PersonEntity person, PersonalUserEntity personalUser);

    // --- NEW METHOD: DTO to PersonEntity ---
    // MapStruct will automatically map fields with the same name:
    // dto.id -> entity.id
    // dto.firstName -> entity.firstName
    // dto.firstSurname -> entity.firstSurname
    // The 'username' from PersonDTO will be ignored here as PersonEntity doesn't have it.
    @Mapping(target = "id", source = "id") // Explicit for clarity, but often optional if names match
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "firstSurname", source = "firstSurname")
    // If PersonEntity had other fields you didn't want to map from the DTO,
    // you could add @Mapping(target="otherFieldInEntity", ignore=true)
    PersonEntity toPersonEntity(PersonDTO personDTO);

    // --- NEW METHOD: DTO to PersonalUserEntity ---
    // This would map the username part of the DTO to a PersonalUserEntity
    @Mapping(target = "username", source = "username")
    @Mapping(target = "password", source = "password")
    // If PersonalUserEntity had other fields, you'd map them or ignore them.
    // For example, if PersonalUserEntity had an 'id' field not present in PersonDTO,
    // and you didn't want to set it:
    // @Mapping(target="id", ignore=true)
    PersonalUserEntity toPersonalUserEntity(PersonDTO personDTO);

    // --- NEW METHOD: Updating an existing PersonEntity from a PersonDTO ---
    // This is useful for PATCH operations or when you want to update an already loaded entity.
    // The @MappingTarget annotation tells MapStruct to update the provided 'entity'
    // instead of creating a new one.
    @Mapping(target = "id", ignore = true) // Typically, you don't update the ID
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "firstSurname", source = "firstSurname")
    void updatePersonEntityFromDto(PersonDTO dto, @MappingTarget PersonEntity entity);

    // --- NEW METHOD: Updating an existing PersonalUserEntity from a PersonDTO ---
    @Mapping(target = "username", source = "username")
    // If PersonalUserEntity has an ID or other fields you don't want to update from DTO:
    // @Mapping(target="id", ignore=true)
    void updatePersonalUserEntityFromDto(PersonDTO dto, @MappingTarget PersonalUserEntity personalUser);
}