package pl.jaworskimateuszm.myleagues.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.jaworskimateuszm.myleagues.mapper.PlaceMapper;
import pl.jaworskimateuszm.myleagues.model.Place;

import javax.validation.Valid;

@Controller
@RequestMapping("/places")
public class PlaceController {

	private PlaceMapper placeMapper;

	public PlaceController(PlaceMapper placeMapper) {
		this.placeMapper = placeMapper;
	}

	@GetMapping("/list")
	public String listPlaces(Model model) {
		List<Place> places = placeMapper.findAll();
		model.addAttribute("places", places);
		return "/places/list-places";
	}
	
	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("place", new Place());
		return "/places/place-form";
	}

	@GetMapping("/update")
	public String update(@RequestParam("placeId") int id, Model model) {
		Place place = placeMapper.findById(id);
		model.addAttribute("place", place);
		return "/places/place-form";
	}
	
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("place") Place place, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("error", true);
			return "redirect:/places/add";
		}
		if (place.getConfirmedFlag())
			place.setConfirmed(1);
		else
			place.setConfirmed(0);
		Place found = placeMapper.findById(place.getPlaceId());
		if (found != null)
			placeMapper.update(place);
		else
			placeMapper.insert(place);

		return "redirect:/places/list";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("placeId") int id) {
		placeMapper.deleteById(id);
		return "redirect:/places/list";
	}

	@GetMapping("/search")
	public String search(@RequestParam("name") String name, Model model) {
		List<Place> places = placeMapper.findAllByName(name);
		model.addAttribute("places", places);
		return "/places/list-places";
	}
}