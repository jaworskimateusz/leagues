package pl.jaworskimateuszm.myleagues.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.jaworskimateuszm.myleagues.mapper.GameMapper;
import pl.jaworskimateuszm.myleagues.mapper.LeagueMapper;
import pl.jaworskimateuszm.myleagues.mapper.RoundMapper;
import pl.jaworskimateuszm.myleagues.mapper.SeasonMapper;
import pl.jaworskimateuszm.myleagues.model.Game;
import pl.jaworskimateuszm.myleagues.model.League;
import pl.jaworskimateuszm.myleagues.model.Round;
import pl.jaworskimateuszm.myleagues.model.Season;
import pl.jaworskimateuszm.myleagues.utils.Parser;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;

@Controller
@RequestMapping("/rounds")
public class RoundController {

	private RoundMapper roundMapper;
	private GameMapper gameMapper;

	public RoundController(RoundMapper roundMapper, GameMapper gameMapper) {
		this.roundMapper = roundMapper;
		this.gameMapper = gameMapper;
	}

	@GetMapping("/list")
	public String listRounds(Model model) {
		ArrayList<Round> rounds = new ArrayList<>();
		rounds.add(new Round(1,2,25, "Tenis ziemny"));
		//List<Round> rounds = roundMapper.findAll();
		
		model.addAttribute("rounds", rounds);
		return "/rounds/list-rounds";
	}
	
	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("round", new Round());
//		List<Game> games = gameMapper.findAll();
		ArrayList<Game> games = new ArrayList<>();
		games.add(new Game(2,6,new Date(),"Warszawianka",1,2,3,4));
		games.add(new Game(3,7,new Date(),"Spodek Katowicki",1,2,3,4));
		model.addAttribute("games", games);
		return "/rounds/round-form";
	}

	@GetMapping("/update")
	public String update(@RequestParam("roundId") int id, Model model) {
//		Round round = roundMapper.findById(id);
//		model.addAttribute("round", round);
//		model.addAttribute("games", games);
		model.addAttribute("round",new Round(1,2,25, "Tenis ziemny"));
		return "/rounds/round-form";
	}
	
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("round") Round round, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("error", true);
			return "redirect:/rounds/add";
		}

//		here payment should be automatically created for specific round
//		roundMapper.save(round);
		return "redirect:/rounds/list";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("roundId") int id) {
//		roundMapper.deleteById(id);
		return "redirect:/rounds/list";
	}

	@GetMapping("/search")
	public String search(@RequestParam("number") String num,
						 @RequestParam("confirm") int confirm,
						 @RequestParam("playerId") int playerId,
						 Model model,
						 RedirectAttributes redirectAttributes) {
		int number = Parser.stringToInt(num);
		if (number == -1) {
			redirectAttributes.addFlashAttribute("error", true);
			return "redirect:/rounds/list";
		}
		//List<Round> rounds = roundMapper.searchBy(number);
		ArrayList<Round> rounds = new ArrayList<>();
		rounds.add(new Round(1,2,25, "Tenis ziemny"));
		model.addAttribute("rounds", rounds);
		model.addAttribute("confirm", confirm);
		model.addAttribute("playerId", playerId);

		return "/rounds/list-rounds";
	}

	@GetMapping("/manage-rounds")
	public String manageRoundPayment(@RequestParam("playerId") int id, Model model) {
//		Player player = playerMapper.findById(id);
//		List<Round> rounds = roundMapper.findAll();

		ArrayList<Round> rounds = new ArrayList<>();
		rounds.add(new Round(1,2,25, "Tenis ziemny"));
		model.addAttribute("rounds", rounds);
		model.addAttribute("confirm", 1);
		model.addAttribute("playerId", id);
		return "/rounds/list-rounds";
	}

	@GetMapping("/confirm-payment")
	public String confirmPayment(@RequestParam("roundId") int roundId,
								 @RequestParam("playerId") int playerId,
								 Model model) {
//		List<Round> rounds = roundMapper.findAll();
//		TODO confirm payment where roundId is roundId
//		TODO get rounds for playerId
		ArrayList<Round> rounds = new ArrayList<>();
		model.addAttribute("rounds", rounds);
		model.addAttribute("playerId", playerId);
		model.addAttribute("confirm", 1);
		return "/rounds/list-rounds";
	}

	@GetMapping("/cancel-payment")
	public String cancelPayment(@RequestParam("roundId") int roundId,
								@RequestParam("playerId") int playerId,
								Model model) {
//		List<Round> rounds = roundMapper.findAll();
		ArrayList<Round> rounds = new ArrayList<>();
		model.addAttribute("rounds", rounds);
		model.addAttribute("playerId", playerId);
		model.addAttribute("confirm", 1);
		return "/rounds/list-rounds";
	}

}