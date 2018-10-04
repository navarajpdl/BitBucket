package com.rsrit.bitbucket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.rsrit.bitbucket.model.BitBucketDetailResponse;
import com.rsrit.bitbucket.model.BitBucketResponse;
import com.rsrit.bitbucket.service.BitBucketService;

@RestController
public class BitBucketController {
	private BitBucketService bitBucketService;

	@Autowired
	public BitBucketController(BitBucketService bitBucketService) {
		this.bitBucketService = bitBucketService;
	}

	@GetMapping("/hello")
	public String hello() {
		return "Hello Test ";
		// System.out.println("Hello test");
	}

	// api url for all repositories for given user
	//"produces = { \"application/json\", "\"application/xml\" 
	
	@GetMapping(value = "/getrpos/{username}/{password}")
	public BitBucketResponse getAllRepositories(@PathVariable String username, @PathVariable String password) {

		// Return all repositories of user
		return bitBucketService.getAllRepositories(username, password);
	}

	// api url for all repositories details
	@GetMapping(value = "/getrpos/{username}/{password}/{repository}/{reposlug}")
	public BitBucketDetailResponse getRepositoriesDetail(@PathVariable String username, @PathVariable String password,
			@PathVariable String repository, @PathVariable String reposlug) {
		return bitBucketService.getRepositoriesDetail(username, password, repository, reposlug);
	}

	// api url for all repositories action
	
	@GetMapping(value = "/repositoryaction/{username}/{password}/{repository}/{action}")
	public BitBucketResponse getRepositoriesAction(@PathVariable String username, @PathVariable String password,
			@PathVariable String repository, @PathVariable String action) {
		return bitBucketService.getRepositoryAction(username, password, repository, action);
	}

	// api url for all branch action
	@GetMapping(value = "/branchaction/{username}/{password}/{repository}/{reposlug}/{branchname}/{action}")
	public BitBucketResponse getBranchAction(@PathVariable String username, @PathVariable String password,
			@PathVariable String repository, @PathVariable String reposlug, @PathVariable String branchname,
			@PathVariable String action) {
		return bitBucketService.getBranchAction(username, password, repository, reposlug, branchname, action);
	}
}
