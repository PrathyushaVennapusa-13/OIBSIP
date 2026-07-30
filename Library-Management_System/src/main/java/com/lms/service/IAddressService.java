package com.lms.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.lms.dto.AddressDto;
import com.lms.entity.Address;
import com.lms.exception.AddressNotFoundException;


public interface IAddressService {

	public ResponseEntity<AddressDto> saveAddress(AddressDto addressDto);
	public ResponseEntity<String> deleteRow(int addressId)throws AddressNotFoundException;
	public ResponseEntity<Address> findByAddressId(int addressId)  throws AddressNotFoundException;
	public ResponseEntity<String> updateAddress(AddressDto addressDto);
	public ResponseEntity<String> updateAddressByColumn(AddressDto addressDto, int addressId)throws AddressNotFoundException;
	public ResponseEntity<List<Address>> findAllAddress();
	
	
}
