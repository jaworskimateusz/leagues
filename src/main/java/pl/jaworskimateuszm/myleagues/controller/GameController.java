package pl.jaworskimateuszm.myleagues.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.jaworskimateuszm.myleagues.mapper.GameMapper;
import pl.jaworskimateuszm.myleagues.mapper.LeagueMapper;
import pl.jaworskimateuszm.myleagues.mapper.SeasonMapper;
import pl.jaworskimateuszm.myleagues.model.Game;
import pl.jaworskimateuszm.myleagues.model.GameSet;
import pl.jaworskimateuszm.myleagues.model.League;
import pl.jaworskimateuszm.myleagues.model.Season;
import pl.jaworskimateuszm.myleagues.utils.Parser;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;

@Controller
@RequestMapping("/games")
public class GameController {

	private GameMapper gameMapper;

	public GameController(GameMapper gameMapper) {
		this.gameMapper = gameMapper;
	}

	@GetMapping("/list")
	public String listGames(Model model) {
		ArrayList<Game> games = new ArrayList<>();
		games.add(new Game(5,7,new Date(),"Spodek Katowicki",1,2,3,4));

		//List<Game> games = gameMapper.findAll();
		
		model.addAttribute("games", games);
		return "/games/list-games";
	}
	
	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("game", new Game());
//		List<Game> games = gameMapper.findAll();
		ArrayList<GameSet> gameSets = new ArrayList<>();
		gameSets.add(new GameSet());
		model.addAttribute("gameSets", gameSets);
		return "/games/game-form";
	}

	@GetMapping("/update")
	public String update(@RequestParam("gameId") int id, Model model) {
//		Game game = gameMapper.findById(id);
//		model.addAttribute("game", game);
//		model.addAttribute("gameSets", gameSets);
		model.addAttribute("game", new Game(5,7,new Date(),"Spodek Katowicki",1,2,3,4));
		return "/games/game-form";
	}
	
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("game") Game game, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("error", true);
			return "redirect:/games/add";
		}
//		gameMapper.save(game);
		return "redirect:/games/list";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("gameId") int id) {
//		gameMapper.deleteById(id);
		return "redirect:/games/list";
	}

	@GetMapping("/search")
	public String search(@RequestParam("place") String place, Model model, RedirectAttributes redirectAttributes) {
//		List<Game> games = gameMapper.searchBy(place);
//		model.addAttribute("games", games);
		ArrayList<Game> games = new ArrayList<Game>();
		model.addAttribute("notFound", games.isEmpty());
		return "/games/list-games";
	}
}