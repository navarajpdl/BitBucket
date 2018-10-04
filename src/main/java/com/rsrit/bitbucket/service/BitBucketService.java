package com.rsrit.bitbucket.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.rsrit.bitbucket.model.BitBucketDetailResponse;
import com.rsrit.bitbucket.model.BitBucketResponse;
import com.rsrit.bitbucket.util.RestTemplateResponse;

@Service
public class BitBucketService {
	private RestTemplateResponse restTemplateResponse;

	// https://api.bitbucket.org/1.0/user/repositories
	// https://bitbucket.org/navarajpdl/repositories
	//https://api.bitbucket.org/2.0/users/navarajpdl
	
	
	private String getAllReposUrl = "https://api.bitbucket.org/1.0/user/repositories";
	private String bitbucketrepoactionUrl = "https://api.bitbucket.org/2.0/repositories/%s/%s";
	private String bitbucketbarnchactionUrl = "https://api.bitbucket.org/2.0/repositories/%s/%s";
	private String getBBCloneUrl = "https://api.bitbucket.org/2.0/repositories/%s/%s";
	private String getBBBranchUrl = "https://api.bitbucket.org/2.0/repositories/%s/%s/refs/branches";
	private String getBBcommithUrl = "https://api.bitbucket.org/2.0/repositories/%s/$s/commits";

	@Autowired
	public BitBucketService(RestTemplateResponse restTemplateResponse) {
		this.restTemplateResponse = restTemplateResponse;
	}

	// call the getResponseEntity method from restTempalte
	// return status and responseEntity

	public BitBucketResponse getAllRepositories(String username, String password) {
		ResponseEntity responseEntity = restTemplateResponse.getResponseEntity(getAllReposUrl, username, password);
		if (responseEntity != null) {
			return new BitBucketResponse(1, responseEntity.toString());
		}
		return new BitBucketResponse();
	}

	public BitBucketDetailResponse getRepositoriesDetail(String username, String password, String repository,
			String reposlug) {
		ResponseEntity bitbucketclone = getBBClone(username, password, repository, reposlug);
		ResponseEntity bitbucketbranches = getBBBranch(username, password, repository, reposlug);
		ResponseEntity bitbucketcommit = getBBCommit(username, password, repository, reposlug);

		List<ResponseEntity> list = new ArrayList<>();
		list.add(bitbucketclone);
		list.add(bitbucketbranches);
		list.add(bitbucketcommit);

		return new BitBucketDetailResponse(list);
	}

	public BitBucketResponse getRepositoryAction(String username, String password, String repository, String action) {
		ResponseEntity responseEntity = restTemplateResponse.getResponseEntityForRepositoryAction(
				String.format(bitbucketrepoactionUrl, username, repository), username, password, action);
		if (responseEntity != null) {
			return new BitBucketResponse(1, responseEntity.toString());
		}
		return new BitBucketResponse();
	}

	//
	public BitBucketResponse getBranchAction(String username, String password, String repository, String repoSlug,
			String branchName, String action) {
		String url = "";
		if (action.equalsIgnoreCase("POST")) {
			url = String.format(bitbucketrepoactionUrl, repository, repoSlug) + "/src";
		} else if (action.equalsIgnoreCase("DELETE")) {
			url = String.format(bitbucketbarnchactionUrl, repository, repoSlug) + "/_branch/" + branchName;
		}
		ResponseEntity responseEntity = restTemplateResponse.getResponseEntityForBranchAction(url, username, password,
				action);
		if (responseEntity != null) {
			return new BitBucketResponse(1, responseEntity.toString());
		}
		return new BitBucketResponse();
	}

	// to enable Asynchronous
	// can return any one which ever ready

	@Async
	ResponseEntity getBBClone(String username, String password, String repository, String reposlug) {
		return restTemplateResponse.getResponseEntity(String.format(getBBCloneUrl, repository, reposlug), username,
				password);
	}

	@Async
	ResponseEntity getBBBranch(String username, String password, String repository, String reposlug) {
		return restTemplateResponse.getResponseEntity(String.format(getBBBranchUrl, repository, reposlug), username,
				password);
	}

	@Async
	ResponseEntity getBBCommit(String username, String password, String repository, String reposlug) {
		return restTemplateResponse.getResponseEntity(String.format(getBBcommithUrl, repository, reposlug), username,
				password);
	}
}
