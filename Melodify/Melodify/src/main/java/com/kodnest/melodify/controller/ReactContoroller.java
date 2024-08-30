package com.kodnest.tunehub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kodnest.tunehub.entity.Playlist;
import com.kodnest.tunehub.entity.Song;
import com.kodnest.tunehub.entity.User;
import com.kodnest.tunehub.model.LoginRequest;
import com.kodnest.tunehub.service.PlaylistService;
import com.kodnest.tunehub.service.SongService;
import com.kodnest.tunehub.serviceimpl.UserServiceImpl;

@CrossOrigin("*")
@RestController
public class ReactContoroller {
	@Autowired
	UserServiceImpl serviceImpl;

	@Autowired
	SongService songService;
	
	@Autowired
	PlaylistService playlistService;

	@PostMapping("/api/loginrequest")
	public ResponseEntity<String> postMethodName(@RequestBody LoginRequest data) {
		String nameorEmail = data.getNameorEmail();
		String password = data.getPassword();
		System.out.println(nameorEmail);

		boolean exists = serviceImpl.usernameorEmailExists(nameorEmail);
		System.out.println(exists);
		if (exists) {
			boolean auth = serviceImpl.authenticateUser(nameorEmail, password);
			System.out.println(auth);
			if (auth) {
				User user = serviceImpl.findByusernameorEmail(nameorEmail);
				String role = user.getRole();
				System.out.println(role);
				if (role.equals("admin")) {
					return ResponseEntity.ok().body("adminhome");
				} else if (role.equals("customer")) {
					return ResponseEntity.ok().body("customerhome");
				} else {
					return ResponseEntity.ok().body("unknown");
				}
			}else {
				return ResponseEntity.ok().body("Incorrect password, Please try Again");
			}
		} else {
			return ResponseEntity.ok().body("User doesn't exists");
		}
	}
		
	@PostMapping("/api/registration")
	public String addUser(@RequestBody User user) {
		// email taken from the regisgtration form
		String email = user.getEmail();
		// checking if the email as enterd in registration form
		boolean status = serviceImpl.emailExists(email);
		if (status == false) {
			//System.out.println(user);
			serviceImpl.addUser(user);
			System.out.println(user+"user Added");
		} else {
			System.out.println("User already Exist");
		}
		return "success";
	}
	
	@PostMapping("/api/addsong")
	public String addsong(@RequestBody Song song) {
		boolean songStatus = songService.songExists(song.getName());
		if(songStatus ==false) {
			songService.addSong(song);
			System.out.println("song added successfully");
		}else {
			System.out.println("song already exists");
		}
		return "success";
	}
	
	@GetMapping("/api/viewsong")
	public List<Song> viewsong(){
		return songService.fetchAllSongs();
	}
	
	@PostMapping("/api/createplaylists")
	public void createplaylists(@RequestBody Playlist playlist) {
		System.out.println(playlist);
		 playlistService.addplaylist(playlist);
	}
	
//	@PostMapping("/api/addplaylist")
//	public String addplaylist(@RequestBody Playlist playlist) {
//	    System.out.println("Received Playlist: " + playlist);		
//	    playlistService.addplaylist(playlist);
////		List<Song> songList = playlist.getSongs();
////		for(Song s : songList) {
////			s.getPlaylist().add(playlist);
////			songService.updateSong(s);
////		}
//		return "success";
//	}
	
	@GetMapping("/api/viewplaylists")
	public List<Playlist> viewplaylists(Model model) {
		List<Playlist> allPlaylists = playlistService.fetchAllSongs();
		//		System.out.println(songList);
		model.addAttribute("playlist",allPlaylists);
		return playlistService.fetchAllSongs();
	}
}


