package pl.jaworskimateuszm.myleagues.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.jaworskimateuszm.myleagues.mapper.RoundMapper;
import pl.jaworskimateuszm.myleagues.mapper.SeasonMapper;
import pl.jaworskimateuszm.myleagues.model.Round;
import pl.jaworskimateuszm.myleagues.model.Season;
import pl.jaworskimateuszm.myleagues.utils.Parser;

import javax.validation.Valid;
import java.util.Date;

@Controller
@RequestMapping("/rounds")
public class RoundController {

	private RoundMapper roundMapper;
	private SeasonMapper seasonMapper;

	public RoundController(RoundMapper roundMapper, SeasonMapper seasonMapper) {
		this.roundMapper = roundMapper;
		this.seasonMapper = seasonMapper;
	}

	@GetMapping("/list")
	public String listRounds(Model model) {
		List<Round> rounds = roundMapper.findAll();
		model.addAttribute("rounds", rounds);
		return "/rounds/list-rounds";
	}
	
	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("round", new Round());
		List<Season> seasons = seasonMapper.findAll();
		model.addAttribute("seasons", seasons);
		return "/rounds/round-form";
	}

	@GetMapping("/update")
	public String update(@RequestParam("roundId") int id, Model model) {
		Round round = roundMapper.findById(id);
		List<Season> seasons = seasonMapper.findAll();
		model.addAttribute("round", round);
		model.addAttribute("seasons", seasons);
		return "/rounds/round-form";
	}
	
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("round") Round round, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("error", true);
			return "redirect:/rounds/add";
		}
		if (roundMapper.findById(round.getRoundId()) != null) {
			roundMapper.update(round);
		} else {
			roundMapper.insert(round);
		}
		roundMapper.insertFee(new Date()); //type 0 means for rounds
		int feeId = roundMapper.findLastFeeId();
		roundMapper.insertRoundFee(round.getRoundId(), feeId);
		return "redirect:/rounds/list";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("roundId") int id) {
		roundMapper.deleteRoundFeeById(id);
		roundMapper.deleteById(id);
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
		List<Round> rounds = roundMapper.findAllByNumber(number);
		model.addAttribute("rounds", rounds);
		model.addAttribute("confirm", confirm);
		model.addAttribute("playerId", playerId);

		return "/rounds/list-rounds";
	}

	@GetMapping("/manage-rounds")
	public String manageRoundPayment(@RequestParam("playerId") int id, Model model) {
		List<Round> rounds = roundMapper.findAllByPlayerId(id);
		model.addAttribute("rounds", rounds);
		model.addAttribute("playerId", id);
		model.addAttribute("confirm", 1);
		return "/rounds/list-rounds";
	}

	@GetMapping("/confirm-payment")
	public String confirmPayment(@RequestParam("roundId") int roundId,
								 @RequestParam("playerId") int playerId,
								 RedirectAttributes redirectAttributes) {
		List<Round> rounds = roundMapper.findAllByPlayerId(playerId);
		for (Round round : rounds) {
			if (round.getRoundId() == roundId) {
				roundMapper.confirmRoundFee(round.getFeeId());
			}
		}
		redirectAttributes.addAttribute("playerId", playerId);
		return "redirect:/rounds/manage-rounds";
	}

	@GetMapping("/cancel-payment")
	public String cancelPayment(@RequestParam("roundId") int roundId,
								@RequestParam("playerId") int playerId,
								RedirectAttributes redirectAttributes) {
		List<Round> rounds = roundMapper.findAllByPlayerId(playerId);
		for (Round round : rounds) {
			if (round.getRoundId() == roundId) {
				roundMapper.cancelRoundFee(round.getFeeId());
			}
		}
		redirectAttributes.addAttribute("playerId", playerId);
		return "redirect:/rounds/manage-rounds";
	}

}