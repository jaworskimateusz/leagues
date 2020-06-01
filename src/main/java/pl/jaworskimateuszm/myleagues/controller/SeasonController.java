package pl.jaworskimateuszm.myleagues.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.jaworskimateuszm.myleagues.mapper.LeagueMapper;
import pl.jaworskimateuszm.myleagues.mapper.PlayerMapper;
import pl.jaworskimateuszm.myleagues.mapper.SeasonMapper;
import pl.jaworskimateuszm.myleagues.model.League;
import pl.jaworskimateuszm.myleagues.model.Player;
import pl.jaworskimateuszm.myleagues.model.Season;
import pl.jaworskimateuszm.myleagues.utils.Parser;

import javax.validation.Valid;
import java.util.ArrayList;

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
		ArrayList<Season> seasons = new ArrayList<>();
		seasons.add(new Season(1,2,25, "Sezon wiosenny 2018"));
		seasons.add(new Season(2,11,2552112, "Sezon letni 2018"));
		seasons.add(new Season(12,52,22115, "Sezon wiosenny 2018"));
		seasons.add(new Season(92,18,5125, "Sezon zimowy 2019"));
		//List<Season> seasons = seasonMapper.findAll();
		
		model.addAttribute("seasons", seasons);
		return "/seasons/list-seasons";
	}
	
	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("season", new Season());
//		List<League> leagues = leaguesMapper.findAll();
		ArrayList<League> leagues = new ArrayList<>();
		leagues.add(new League(2,6,6,"WYS","Liga tenisa stołowego"));
		leagues.add(new League(4,7,1,"ŚRED","Liga tenisa ziemnego"));
		leagues.add(new League(7,8,2,"POCZ","Liga squatch'a"));
		model.addAttribute("leagues", leagues);
		return "/seasons/season-form";
	}

	@GetMapping("/update")
	public String update(@RequestParam("seasonId") int id, Model model) {
//		Season season = seasonMapper.findById(id);
//		model.addAttribute("season", season);
//		model.addAttribute("leagues", leagues);
		model.addAttribute("season", new Season(1,2,25, "Sezon wiosenny 2018"));
		return "/seasons/season-form";
	}
	
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("season") Season season, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("error", true);
			return "redirect:/seasons/add";
		}
//		seasonMapper.save(season);
		return "redirect:/seasons/list";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("seasonId") int id) {
//		seasonMapper.deleteById(id);
		return "redirect:/seasons/list";
	}

	@GetMapping("/search")
	public String search(@RequestParam("number") String num, Model model, RedirectAttributes redirectAttributes) {
		int number = Parser.stringToInt(num);
		if (number == -1) {
			redirectAttributes.addFlashAttribute("error", true);
			return "redirect:/seasons/list";
		}
//		List<Season> seasons = seasonMapper.searchBy(number);
		//model.addAttribute("seasons", seasons);
		ArrayList<Season> seasons = new ArrayList<Season>();
		model.addAttribute("notFound", seasons.isEmpty());
		return "/seasons/list-seasons";
	}
}