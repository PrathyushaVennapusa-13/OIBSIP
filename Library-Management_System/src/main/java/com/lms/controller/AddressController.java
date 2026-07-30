package com.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.dto.AddressDto;
import com.lms.entity.Address;
import com.lms.service.IAddressService;

@RestController
@RequestMapping("/address")
public class AddressController {

	final IAddressService addressService;

	AddressController(IAddressService addressService) {
		this.addressService = addressService;
	}

	@PostMapping
	public ResponseEntity<AddressDto> saveAddress(@RequestBody AddressDto addressDto) {
		return addressService.saveAddress(addressDto);
	}

	@DeleteMapping("/{addressId}")
	public ResponseEntity<String> deleteRow(@PathVariable int addressId) {
		return addressService.deleteRow(addressId);
	}

	@GetMapping("/{addressId}")
	public ResponseEntity<Address> findByAddressId(@PathVariable int addressId) {
		return addressService.findByAddressId(addressId);
	}

	@PutMapping
	public ResponseEntity<String> updateAddress(@RequestBody AddressDto addressDto) {
		return addressService.updateAddress(addressDto);
	}

	@PatchMapping("/{addressId}")
	public ResponseEntity<String> updateAddressByColumn(@RequestBody AddressDto addressDto, @PathVariable int addressId) {
		return addressService.updateAddressByColumn(addressDto, addressId);
	}

	@GetMapping
	public ResponseEntity<List<Address>> findAllAddress() {
		return addressService.findAllAddress();
	}

}
