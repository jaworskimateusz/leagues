package pl.jaworskimateuszm.myleagues.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.jaworskimateuszm.myleagues.mapper.GameMapper;
import pl.jaworskimateuszm.myleagues.mapper.PlayerMapper;
import pl.jaworskimateuszm.myleagues.mapper.RoundMapper;
import pl.jaworskimateuszm.myleagues.model.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;

@Controller
@RequestMapping("/games")
public class GameController {

	private GameMapper gameMapper;
	private PlayerMapper playerMapper;
	private RoundMapper roundMapper;

	public GameController(GameMapper gameMapper, PlayerMapper playerMapper, RoundMapper roundMapper) {
		this.gameMapper = gameMapper;
		this.playerMapper = playerMapper;
		this.roundMapper = roundMapper;
	}

	@GetMapping("/list")
	public String listGames(Model model) {
		List<Game> games = gameMapper.findAll();
		model.addAttribute("games", games);
		return "/games/list-games";
	}
	
	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("game", new Game());
		List<Round> rounds = roundMapper.findAll();
		model.addAttribute("rounds",rounds);
		return "/games/game-form";
	}

	@GetMapping("/update")
	public String update(@RequestParam("gameId") int id, Model model) {
		Game game = gameMapper.findById(id);
		List<Round> rounds = roundMapper.findAll();
//		Player firstPlayer = playerMapper.findById(game.firstPlayerId);
		ArrayList<GameSet> gameSets = new ArrayList<>();
		gameSets.add(new GameSet(10, 1, 15, 8));
//		TODO get sets for game from db
		Player firstPlayer = new Player(3, "Jakub", "Zieliński", "92020212321");
		Player secondPlayer = new Player(4, "Mateusz", "Kowalski", "91020212321");

		model.addAttribute("game", game);
		model.addAttribute("firstPlayer", firstPlayer);
		model.addAttribute("secondPlayer", secondPlayer);
		model.addAttribute("secondPlayer", secondPlayer);
		model.addAttribute("rounds", rounds);
		model.addAttribute("gameSets", gameSets);
		return "/games/game-form";
	}
	
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("game") Game game, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("error", true);
			return "redirect:/games/add";
		}
		if (gameMapper.findById(game.getGameId()) != null) {
			gameMapper.update(game);
		} else {
			gameMapper.insert(game);
		}
		return "redirect:/games/list";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("gameId") int id) {
//		gameMapper.deleteGamePlayerById(id);
		gameMapper.deleteById(id);
		return "redirect:/games/list";
	}

	@GetMapping("/search")
	public String search(@RequestParam("place") String place,
						 @RequestParam("dateFrom") String dateFrom,
						 @RequestParam("dateTo") String dateTo,
						 Model model) {
		List<Game> games = gameMapper.findAllByPlace(place);
		model.addAttribute("games", games);
		return "/games/list-games";
	}

	@GetMapping("/add-to-game")
	public String addToGame(@RequestParam("playerId") int playerId,
							@RequestParam("gameId") int gameId,
							@RequestParam("whichOne") String whichOne,
							Model model) {
//		Game game = gameMapper.findById(id);
//		model.addAttribute("game", game);
//		TODO db operations
		return "/games/game-form";
	}

	@GetMapping("/add-set")
	public String add(@RequestParam("gameId") int gameId, Model model) {
		model.addAttribute("gameSet", new GameSet());
		model.addAttribute("gameId", gameId);
		return "/games/set-form";
	}

	@GetMapping("/update-set")
	public String updateSet(@RequestParam("gameId") int gameId, @RequestParam("gameSetId") int gameSetId, Model model) {
		ArrayList<GameSet> gameSets = new ArrayList<>();
		model.addAttribute("gameSet", new GameSet(10, 1, 15, 8));
		model.addAttribute("gameId", gameId);
		return "/games/set-form";
	}

	@PostMapping("/save-set")
	public String saveSet(@RequestParam("gameId") int gameId,
						  @ModelAttribute("gameSet") GameSet gameSet,
						  Model model,
						  RedirectAttributes redirectAttributes) {

//		gameMapper.saveSet(gameSet);
		redirectAttributes.addAttribute("gameId", gameId);
		return "redirect:/games/update";
	}

	@GetMapping("/delete-set")
	public String deleteSet(@RequestParam("gameId") int gameId, @RequestParam("gameSetId") int gameSetId, RedirectAttributes redirectAttributes) {
//		gameMapper.deleteSetById(id);
		redirectAttributes.addAttribute("gameId", gameId);
		return "redirect:/games/update";
	}

}