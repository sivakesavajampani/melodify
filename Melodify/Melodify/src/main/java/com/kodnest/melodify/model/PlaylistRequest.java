package com.kodnest.tunehub.model;

import java.util.List;

import com.kodnest.tunehub.entity.Song;

public class PlaylistRequest {
	String name;
	List<Song> songs;
	
	
	public PlaylistRequest() {
		super();
		// TODO Auto-generated constructor stub
	}


	public PlaylistRequest(String name, List<Song> songs) {
		super();
		this.name = name;
		this.songs = songs;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<Song> getSongs() {
		return songs;
	}


	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}


	@Override
	public String toString() {
		return "PlaylistRequest [name=" + name + ", songs=" + songs + "]";
	}
	
	
	

}
