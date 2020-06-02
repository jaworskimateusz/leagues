package pl.jaworskimateuszm.myleagues.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.jaworskimateuszm.myleagues.mapper.PlaceMapper;
import pl.jaworskimateuszm.myleagues.model.Place;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

@Controller
@RequestMapping("/places")
public class PlaceController {

	private PlaceMapper placeMapper;

	public PlaceController(PlaceMapper placeMapper) {
		this.placeMapper = placeMapper;
	}

	@GetMapping("/list")
	public String listPlaces(Model model) {
		//List<Place> places = placeMapper.findAll();
		ArrayList<Place> places = new ArrayList<>();
		places.add(new Place(1, new BigDecimal("1000.05"),30,"Spodek Katowice",true));
		places.add(new Place(2, new BigDecimal("2000.05"),35,"Spodek Warszawa",true));
		places.add(new Place(4, new BigDecimal("3000.05"),32,"Spodek Piaseczno",false));
		model.addAttribute("places", places);
		model.addAttribute("places", places);
		return "/places/list-places";
	}
	
	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("place", new Place());
//		List<Place> places = placeMapper.findAll();
		return "/places/place-form";
	}

	@GetMapping("/update")
	public String update(@RequestParam("placeId") int id, Model model) {
//		Place place = placeMapper.findById(id);
//		model.addAttribute("place", place);
		model.addAttribute("place", new Place(1, new BigDecimal("1000.05"),30,"Spodek Katowice",true));
		return "/places/place-form";
	}
	
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("place") Place place, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("error", true);
			return "redirect:/places/add";
		}

//		placeMapper.save(place);
		return "redirect:/places/list";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("placeId") int id) {
//		placeMapper.deleteById(id);
		return "redirect:/places/list";
	}

	@GetMapping("/search")
	public String search(@RequestParam("name") String name, Model model, RedirectAttributes redirectAttributes) {
//		List<Place> places = placeMapper.searchBy(name);
//		model.addAttribute("places", places);
//		ArrayList<Place> places = new ArrayList<Place>();
		return "/places/list-places";
	}
}