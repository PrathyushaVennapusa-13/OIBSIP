package com.lms.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lms.dto.AddressDto;
import com.lms.entity.Address;
import com.lms.entity.User;
import com.lms.exception.AddressNotFoundException;
import com.lms.repository.AddressRepository;
import com.lms.repository.UserRepository;
import com.lms.service.IAddressService;

@Service

public class AddressService implements IAddressService {

	final ModelMapper modelMapper;
	final AddressRepository addressRepository;
	final HttpHeaders httpHeaders;
	final UserRepository userRepository;

	AddressService(ModelMapper modelMapper, AddressRepository addressRepository, HttpHeaders httpHeaders,
			UserRepository userRepository) {
		this.modelMapper = modelMapper;
		this.addressRepository = addressRepository;
		this.httpHeaders = httpHeaders;
		this.userRepository = userRepository;
	}

	public ResponseEntity<AddressDto> saveAddress(AddressDto addressDto) {
		Address address = modelMapper.map(addressDto, Address.class);
		addressRepository.save(address);
		httpHeaders.add("info", "Address saved successfully");
		ResponseEntity<AddressDto> responseEntity =
				// data,message-HttpHeaders Object,status code
				new ResponseEntity<>(addressDto, httpHeaders, HttpStatus.CREATED);
		return responseEntity;
	}

	public ResponseEntity<String> deleteRow(int addressId) {
		Optional<Address> optional = addressRepository.findById(addressId);

		if (optional.isPresent()) {
			Address address = optional.get();
			User user = address.getUser();
			user.setAddress(null);
			userRepository.save(user);
			addressRepository.delete(address);
			httpHeaders.add("info", "Address deleted successfully");

			ResponseEntity<String> responseEntity =
					// data,message-HttpHeaders Object,status code
					new ResponseEntity<>("Address Deleted successfully", httpHeaders, HttpStatus.OK);
			return responseEntity;
		} else {
			throw new AddressNotFoundException("Address Not Found ");
		}

	}

	@Override
	public ResponseEntity<Address> findByAddressId(int addressId) {
		Optional<Address> optional = addressRepository.findById(addressId);

		if (optional.isPresent()) {
			Address address = optional.get();
			
			httpHeaders.add("info", "Address Found successfully");

			ResponseEntity<Address> responseEntity = new ResponseEntity<>(address, httpHeaders, HttpStatus.OK);
			return responseEntity;

		}

		else {
			throw new AddressNotFoundException("Address Not Found ");
		}
	}

	@Override
	public ResponseEntity<String> updateAddress(AddressDto addressDto) {

		Address address = modelMapper.map(addressDto, Address.class);
		addressRepository.save(address);
		httpHeaders.add("info", "Address Updated successfully");

		ResponseEntity<String> responseEntity =
				// data,message-HttpHeaders Object,status code
				new ResponseEntity<>("Address updated Successfully", httpHeaders, HttpStatus.OK);
		return responseEntity;

	}

	@Override
	public ResponseEntity<String> updateAddressByColumn(AddressDto addressDto, int addressId) {
		Optional<Address> optionalAddress = addressRepository.findById(addressId);
		if (optionalAddress.isPresent()) {
			Address address = optionalAddress.get();
			address.setArea(addressDto.getArea());
			address.setHouseNumber(addressDto.getHouseNumber());
			addressRepository.save(address);
			httpHeaders.add("info", "Address Found successfully");
			return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body("Address Updated Successfully");
		}

		else {
			throw new AddressNotFoundException("Address Not Found ");
		}
	}

	@Override
	public ResponseEntity<List<Address>> findAllAddress() {

		httpHeaders.add("info", "Address Found successfully");

		ResponseEntity<List<Address>> responseEntity =
				// data,message-HttpHeaders Object,status code
				new ResponseEntity<>(addressRepository.findAll(), httpHeaders, HttpStatus.OK);
		return responseEntity;

	}
	
	

}
