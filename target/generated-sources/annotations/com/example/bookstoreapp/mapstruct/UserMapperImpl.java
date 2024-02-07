package com.example.bookstoreapp.mapstruct;

import com.example.bookstoreapp.dto.ReaderResponseDto;
import com.example.bookstoreapp.dto.VerifyResponseDto;
import com.example.bookstoreapp.entity.User;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-07T10:27:11+0400",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl extends UserMapper {

    @Override
    public VerifyResponseDto mapToVerifyDto(User user) {
        if ( user == null ) {
            return null;
        }

        VerifyResponseDto verifyResponseDto = new VerifyResponseDto();

        verifyResponseDto.setId( user.getId() );
        verifyResponseDto.setName( user.getName() );
        verifyResponseDto.setAge( user.getAge() );
        verifyResponseDto.setPassword( user.getPassword() );
        verifyResponseDto.setRole( user.getRole() );

        return verifyResponseDto;
    }

    @Override
    public ReaderResponseDto userToReaderDto(Integer dummy, Set<User> users) {
        if ( dummy == null && users == null ) {
            return null;
        }

        ReaderResponseDto readerResponseDto = new ReaderResponseDto();

        readerResponseDto.setReaders( userSetToReaderDtoSet( users ) );

        return readerResponseDto;
    }

    protected ReaderResponseDto.ReaderDto userToReaderDto1(User user) {
        if ( user == null ) {
            return null;
        }

        ReaderResponseDto.ReaderDto readerDto = new ReaderResponseDto.ReaderDto();

        readerDto.setRole( user.getRole() );

        return readerDto;
    }

    protected Set<ReaderResponseDto.ReaderDto> userSetToReaderDtoSet(Set<User> set) {
        if ( set == null ) {
            return null;
        }

        Set<ReaderResponseDto.ReaderDto> set1 = new LinkedHashSet<ReaderResponseDto.ReaderDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( User user : set ) {
            set1.add( userToReaderDto1( user ) );
        }

        return set1;
    }
}
