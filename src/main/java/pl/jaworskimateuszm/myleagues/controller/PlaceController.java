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
		ArrayList<Place> places = new ArrayList<>();

		//List<Place> places = placeMapper.findAll();
		
		model.addAttribute("places", places);
		return "/places/list-places";
	}
	
	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("place", new Place());
//		List<Place> places = placeMapper.findAll();
		ArrayList<Place> places = new ArrayList<>();
		places.add(new Place(1, new BigDecimal("1000.05"),30,"Spodek Katowice",1));
		model.addAttribute("places", places);
		return "/places/place-form";
	}

	@GetMapping("/update")
	public String update(@RequestParam("placeId") int id, Model model) {
//		Place place = placeMapper.findById(id);
//		model.addAttribute("place", place);
		model.addAttribute("place", new Place(1, new BigDecimal("1000.05"),30,"Spodek Katowice",1));
		return "/places/place-form";
	}
	
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("place") Place place, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("error", true);
			return "redirect:/place/add";
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
//		model.addAttribute("notFound", places.isEmpty());
		return "/places/list-places";
	}
}