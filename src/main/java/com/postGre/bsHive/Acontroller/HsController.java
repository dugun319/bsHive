package com.postGre.bsHive.Acontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.postGre.bsHive.HsService.HsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "/hs")
public class HsController {

	private final HsService hs;
	
	@GetMapping(value = "/hsClassMain")
	public String LecMain () {
		System.out.println("HsController LecMain Start...");
		System.out.println("HsController LecMain End...");
		return "hs/hsClassMain";
	}
	
	@GetMapping(value = "/lecSchedule")
	public String LecSchedule () {
		System.out.println("HsController LecSchedule Start...");
		System.out.println("HsController LecSchedule End...");
		return "hs/lecSchedule";
	}
	
	@GetMapping(value = "/lecAttendance")
	public String LecAttendance () {
		System.out.println("HsController LecAttendance Start...");
		System.out.println("HsController LecAttendance End...");
		return "hs/lecAttendance";
	}
	
	@GetMapping(value = "/lecAssignment")
	public String LecAssignment () {
		System.out.println("HsController LecAssignment Start...");
		System.out.println("HsController LecAssignment End...");
		return "hs/lecAssignment";
	}
	
	@GetMapping(value = "/lecTest")
	public String LecTest () {
		System.out.println("HsController LecTest Start...");
		System.out.println("HsController LecTest End...");
		return "hs/lecTest";
	}
}