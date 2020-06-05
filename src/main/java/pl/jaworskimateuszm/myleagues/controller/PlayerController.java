package pl.jaworskimateuszm.myleagues.controller;

import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.jaworskimateuszm.myleagues.mapper.LeagueMapper;
import pl.jaworskimateuszm.myleagues.mapper.PlayerMapper;
import pl.jaworskimateuszm.myleagues.model.League;
import pl.jaworskimateuszm.myleagues.model.Player;
import pl.jaworskimateuszm.myleagues.model.PlayerDetail;

import javax.validation.Valid;

@Controller
@RequestMapping("/players")
public class PlayerController {

	private PlayerMapper playerMapper;
	private LeagueMapper leagueMapper;

	public PlayerController(PlayerMapper playerMapper, LeagueMapper leagueMapper) {
		this.playerMapper = playerMapper;
		this.leagueMapper = leagueMapper;
	}

	@GetMapping("/list")
	public String listPlayers(Model model) {
		List<Player> players = playerMapper.findAll();
		model.addAttribute("players", players);
		return "/players/list-players";
	}
	
	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("player", new Player());
		List<League> leagues = leagueMapper.findAll();
		model.addAttribute("leagues", leagues);
		return "/players/player-form";
	}

	@GetMapping("/update")
	public String update(@RequestParam("playerId") int id, Model model) {
		Player player = playerMapper.findById(id);
		List<League> leagues = leagueMapper.findAll();
		model.addAttribute("leagues", leagues);
		model.addAttribute("player", player);
		return "/players/player-form";
	}

	@GetMapping("/detail")
	public String detail(@RequestParam("playerId") int id, Model model) {
		Player player = playerMapper.findById(id);
		List<PlayerDetail> details = playerMapper.findAllDetailById(id);
		model.addAttribute("player", player);
		model.addAttribute("details", details);
		return "/players/player-detail";
	}
	
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("player") Player player, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("error", true);
			return "redirect:/players/add";
		}
		if (playerMapper.findById(player.getPlayerId()) != null) {
			playerMapper.update(player);
			Arrays.stream(player.getLeagueIds()).forEach(leagueId -> playerMapper.updatePlayerLeague(player.getPlayerId(), leagueId));
		} else {
			playerMapper.insert(player);
			Arrays.stream(player.getLeagueIds()).forEach(leagueId -> playerMapper.insertPlayerLeague(player.getPlayerId(), leagueId));
		}

		return "redirect:/players/list";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("playerId") int playerId) {
		playerMapper.deletePlayerLeagueById(playerId);
		playerMapper.deleteById(playerId);
		return "redirect:/players/list";
	}
	
	@GetMapping("/search")
	public String search(@RequestParam("pesel") String pesel,
						 @RequestParam("gameId") int gameId,
						 @RequestParam("whichOne") String whichOne,
						 Model model) {
		List<Player> players = playerMapper.searchBy(pesel);
		model.addAttribute("players", players);
		model.addAttribute("gameId", gameId);
		model.addAttribute("whichOne", whichOne.equals("") ? null : whichOne);
		return "/players/list-players";
	}

	@GetMapping("/choose-player")
	public String choosePlayer(@RequestParam("gameId") int gameId, @RequestParam("whichOne") String whichOne, Model model) {
		List<Player> players = playerMapper.findAll();
		model.addAttribute("gameId", gameId);
		model.addAttribute("players", players);
		model.addAttribute("whichOne", whichOne);
		return "/players/list-players";
	}

}