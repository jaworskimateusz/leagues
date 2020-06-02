package pl.jaworskimateuszm.myleagues.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.jaworskimateuszm.myleagues.mapper.PlayerMapper;
import pl.jaworskimateuszm.myleagues.model.League;
import pl.jaworskimateuszm.myleagues.model.Player;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
@RequestMapping("/players")
public class PlayerController {

	private PlayerMapper playerMapper;

	public PlayerController(PlayerMapper playerMapper) {
		this.playerMapper = playerMapper;
	}

	@GetMapping("/list")
	public String listPlayers(Model model) {

		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player(1,2,3, "Maciej", "Kot", "96020212321"));
		players.add(new Player(2,4,6, "Michał", "Woj", "95020212321"));
		players.add(new Player(3,6,9, "Jakub", "Zieliński", "92020212321"));
		players.add(new Player(4,8,12, "Mateusz", "Kowalski", "91020212321"));
		players.add(new Player(5,10,16, "Anna", "Nowak", "97020212321"));

		//List<Player> players = playerMapper.findAll();
		
		model.addAttribute("players", players);
		return "/players/list-players";
	}
	
	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("player", new Player());
		ArrayList<League> leagues = new ArrayList<>();
		leagues.add(new League(2,6,6,"WYS","Liga tenisa stołowego"));
		leagues.add(new League(4,7,1,"ŚRED","Liga tenisa ziemnego"));
		leagues.add(new League(7,8,2,"POCZ","Liga squatch'a"));
		model.addAttribute("leagues", leagues);
		return "/players/player-form";
	}

	@GetMapping("/update")
	public String update(@RequestParam("playerId") int id, Model model) {
//		Player player = playerMapper.findById(id);
//		model.addAttribute("player", player);
		model.addAttribute("player", new Player(1,2,3, "Maciej", "Kot", "99020212321"));
		return "/players/player-form";
	}
	
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("player") Player player, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("error", true);
			return "redirect:/players/add";
		}
//		playerMapper.save(player);
		return "redirect:/players/list";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("playerId") int playerId) {
//		playerMapper.deleteById(playerId);
		return "redirect:/players/list";
	}
	
	@GetMapping("/search")
	public String search(@RequestParam("pesel") String pesel, Model model) {
//		List<Player> players = playerMapper.searchBy(pesel);
		ArrayList<Player> players = new ArrayList<>();
		model.addAttribute("players", players);
		return "/players/list-players";
	}

}