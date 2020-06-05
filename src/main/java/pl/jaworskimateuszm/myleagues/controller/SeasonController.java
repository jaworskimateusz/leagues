package pl.jaworskimateuszm.myleagues.controller;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.jaworskimateuszm.myleagues.mapper.LeagueMapper;
import pl.jaworskimateuszm.myleagues.mapper.SeasonMapper;
import pl.jaworskimateuszm.myleagues.model.League;
import pl.jaworskimateuszm.myleagues.model.Season;
import pl.jaworskimateuszm.myleagues.utils.Parser;

import javax.validation.Valid;

@Controller
@RequestMapping("/seasons")
public class SeasonController {

	private SeasonMapper seasonMapper;
	private LeagueMapper leagueMapper;

	public SeasonController(SeasonMapper seasonMapper, LeagueMapper leagueMapper) {
		this.seasonMapper = seasonMapper;
		this.leagueMapper = leagueMapper;
	}

	@GetMapping("/list")
	public String listSeasons(Model model) {
		List<Season> seasons = seasonMapper.findAll();
		model.addAttribute("seasons", seasons);
		return "/seasons/list-seasons";
	}
	
	@GetMapping("/add")
	public String add(Model model) {
		List<League> leagues = leagueMapper.findAll();
		model.addAttribute("season", new Season());
		model.addAttribute("leagues", leagues);
		return "/seasons/season-form";
	}

	@GetMapping("/update")
	public String update(@RequestParam("seasonId") int id, Model model) {
		Season season = seasonMapper.findById(id);
		List<League> leagues = leagueMapper.findAll();
		model.addAttribute("season", season);
		model.addAttribute("leagues", leagues);
		return "/seasons/season-form";
	}
	
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("season") Season season, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("error", true);
			return "redirect:/seasons/add";
		}
		if (seasonMapper.findById(season.getSeasonId()) != null) {
			seasonMapper.update(season);
		} else {
			seasonMapper.insert(season);
		}
		seasonMapper.insertFee(new Date()); //type 1 means for seasons
		int feeId = seasonMapper.findLastFeeId();
		seasonMapper.insertSeasonFee(season.getSeasonId(), feeId);
		return "redirect:/seasons/list";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("seasonId") int id) {
		seasonMapper.deleteSeasonFeeById(id);
		seasonMapper.deleteById(id);
		return "redirect:/seasons/list";
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
		List<Season> seasons = seasonMapper.findAllByNumber(number);
		model.addAttribute("seasons", seasons);
		model.addAttribute("confirm", confirm);
		model.addAttribute("playerId", playerId);
		return "/seasons/list-seasons";
	}

	@GetMapping("/manage-seasons")
	public String manageSeasonPayment(@RequestParam("playerId") int id, Model model) {
		List<Season> seasons = seasonMapper.findAllByPlayerId(id);
		model.addAttribute("seasons", seasons);
		model.addAttribute("playerId", id);
		model.addAttribute("confirm", 1);
		return "/seasons/list-seasons";
	}

	@GetMapping("/confirm-payment")
	public String confirmPayment(@RequestParam("seasonId") int seasonId,
								 @RequestParam("playerId") int playerId,
								 RedirectAttributes redirectAttributes) {
		List<Season> seasons = seasonMapper.findAllByPlayerId(playerId);
		for (Season season : seasons) {
			if (season.getSeasonId() == seasonId) {
				seasonMapper.confirmSeasonFee(season.getFeeId());
				break;
			}
		}

		redirectAttributes.addAttribute("playerId", playerId);
		return "redirect:/seasons/manage-seasons";
	}

	@GetMapping("/cancel-payment")
	public String cancelPayment(@RequestParam("seasonId") int seasonId,
								@RequestParam("playerId") int playerId,
								RedirectAttributes redirectAttributes) {
		List<Season> seasons = seasonMapper.findAllByPlayerId(playerId);
		for (Season season : seasons) {
			if (season.getSeasonId() == seasonId) {
				seasonMapper.cancelSeasonFee(season.getFeeId());
				break;
			}
		}
		redirectAttributes.addAttribute("playerId", playerId);
		return "redirect:/seasons/manage-seasons";
	}
}