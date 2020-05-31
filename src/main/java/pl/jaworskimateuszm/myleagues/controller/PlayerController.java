package pl.jaworskimateuszm.myleagues.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.jaworskimateuszm.myleagues.mapper.PlayerMapper;
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
		players.add(new Player(1,2,3, "Maciej", "Kot"));
		players.add(new Player(2,4,6, "Michał", "Woj"));
		players.add(new Player(3,6,9, "Jakub", "Zieliński"));
		players.add(new Player(4,8,12, "Mateusz", "Kowalski"));
		players.add(new Player(5,10,16, "Anna", "Nowak"));

		//List<Player> players = playerMapper.findAll();
		
		model.addAttribute("players", players);
		return "/players/list-players";
	}
	
	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("player", new Player());
		return "/players/player-form";
	}

	@GetMapping("/update")
	public String update(@RequestParam("playerId") int id, Model model) {
//		Player player = playerMapper.findById(id);
//		model.addAttribute("player", player);
		model.addAttribute("player", new Player(1,2,3, "Maciej", "Kot"));
		return "/players/player-form";
	}
	
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("player") Player player, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "/players/player-form";
//		playerMapper.save(player);
		return "redirect:/players/list";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("playerId") int playerId) {
//		playerMapper.deleteById(playerId);
		return "redirect:/players/list";
	}
	
	@GetMapping("/search")
	public String search(@RequestParam("playerSurname") String surname, Model theModel) {
		//List<Player> players = playerMapper.searchBy(surname);
		//model.addAttribute("players", players);
		return "/players/list";
	}
}