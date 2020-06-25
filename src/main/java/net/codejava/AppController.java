package net.codejava;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {

	@Autowired
	private ProductService service; 
	@Autowired
	private EstudianteService serviceEst; 
	@Autowired
	private AreaService serviceArea; 
	
	@RequestMapping("/products")
	public String viewProducts(Model model) {
		List<Product> listProducts = service.listAll();
		model.addAttribute("listProducts", listProducts);
		
		return "index";
	}
	
	@RequestMapping("/new")
	public String showNewProductPage(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		
		return "new_product";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("product") Product product) {
		service.save(product);
		
		return "redirect:/products";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("edit_product");
		Product product = service.get(id);
		mav.addObject("product", product);
		
		return mav;
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteProduct(@PathVariable(name = "id") int id) {
		service.delete(id);
		return "redirect:/products";		
	}

	@RequestMapping("/about")
	public String aboutPage() {
		return "about";		
	}

	@RequestMapping("/")
	public String viewHomePage() {
		return "shot-index";		
	}

	
	@RequestMapping("/results")
	public String viewEstudiantes(Model model) {
		List<Estudiante> listEstudiantes = serviceEst.listAll();
		List<Area> listAreas = serviceArea.listAll();
		List<Resultado> listResultados = new ArrayList<Resultado>();

		Resultado resultado = new Resultado();
		Area area = new Area();
		Estudiante estudiante = new Estudiante();

		for(Estudiante e : listEstudiantes)
		{
			for(Area a : listAreas)
			{
				if(e.getId() == a.getIdestudiante())
				{
					Resultado r = new Resultado(a.getId(), a.getNombre(), a.getValor(), a.getIdestudiante(), a.getAno(),
					 e.getId(), e.getNombre1(), e.getNombre2(), e.getApellido1(), e.getApellido2(), e.getIdentificacion(), e.getTipoid());
					 listResultados.add(r);
				}
			}
		}

		model.addAttribute("listResultados", listResultados);
		model.addAttribute("resultado", resultado);
		model.addAttribute("area", area);
		model.addAttribute("estudiante", estudiante);

		return "results";		
	}

	@RequestMapping("/results_area")
	public String viewAreasFiltro(Model model, @ModelAttribute("area") Area area) {
		
		List<Area> listAreas = serviceArea.listAll();
		List<Estudiante> listEstudiantes = serviceEst.listAll();
		List<Area> listAreasFiltradas = new ArrayList<Area>();
		List<Resultado> listResultados = new ArrayList<Resultado>();
		String texto = "";
		for(Area a : listAreas)
		{
			String año1 = "";
			String año2 = "";
			String valor1 = "";
			String valor2 = "";
			String nombre1 = "";
			String nombre2 = "";

			if(area.getAno() != null)
			{
				año1 = a.getAno().toString();
				año2 = area.getAno().toString();
			}
			if(area.getValor() != null)
			{
				valor1 = a.getValor().toString();
				valor2 = area.getValor().toString();
			}
			if(!area.getNombre().equals(""))
			{
				nombre1 = a.getNombre();
				nombre2 = area.getNombre();
			}
			texto = "Año: "+año2+" | Valor: "+valor2+" | Nombre: "+nombre2;
			if(año1.equals(año2) && valor1.equals(valor2) && nombre1.equals(nombre2))
			{
				listAreasFiltradas.add(a);
			}
		}

		for(Estudiante e : listEstudiantes)
		{
			for(Area a : listAreasFiltradas)
			{
				if(e.getId() == a.getIdestudiante())
				{
					Resultado r = new Resultado(a.getId(), a.getNombre(), a.getValor(), a.getIdestudiante(), a.getAno(),
					 e.getId(), e.getNombre1(), e.getNombre2(), e.getApellido1(), e.getApellido2(), e.getIdentificacion(), e.getTipoid());
					 listResultados.add(r);
				}
			}
		}

		model.addAttribute("listResultados", listResultados);
		model.addAttribute("listAreas", listAreasFiltradas);
		model.addAttribute("texto", texto);
		return "results_area";		
	}

	@RequestMapping("/generalidades")
	public String viewGeneralidades(Model model) {
		
		List<Estudiante> listEstudiantes = serviceEst.listAll();
		List<Area> listAreas = serviceArea.listAll();
		List<Area> listAreasFiltradas = new ArrayList<Area>();
		List<Resultado> listResultados = new ArrayList<Resultado>();
		ArrayList<String> nombres = new ArrayList<String>();

		int  cantExamenes = 0;
		for(Estudiante e : listEstudiantes)
		{
			for(Area a : listAreas)
			{
				if(e.getId() == a.getIdestudiante())
				{
					Resultado r = new Resultado(a.getId(), a.getNombre(), a.getValor(), a.getIdestudiante(), a.getAno(),
					 e.getId(), e.getNombre1(), e.getNombre2(), e.getApellido1(), e.getApellido2(), e.getIdentificacion(), e.getTipoid());
					 listResultados.add(r);
					 cantExamenes++;
				}
			}
		}

		for(Area a : listAreas)
		{
			boolean bandera = true;
			for(String n : nombres)
			{
				if(n.equals(a.getNombre()))
				{
					bandera = false;
				}
			}
			if(bandera)
			{
				nombres.add(a.getNombre());
			}
		}
		for(String n : nombres)
		{
			Area area = new Area();
			area.setNombre(n);
			listAreasFiltradas.add(area);
		}
		double promedio = 0.0;
		for(Area a : listAreas)
		{
			for(Area n : listAreasFiltradas)
			{
				double v;
				if(a.getNombre().equals(n.getNombre()))
				{
					if(n.getValor() == null)
					{
						v = 0;
					}
					else
					{
						v = n.getValor();
					}
					v+= a.getValor();
					v = v/2;
					int i = (int)v;
					n.setValor(i);
					promedio += i;
					promedio = promedio/2;
				}
			}
		}
		int areasTotales = listAreas.size();
		promedio = round(promedio, 2);
		model.addAttribute("areas", areasTotales);
		model.addAttribute("examenes", cantExamenes);
		model.addAttribute("promedio", promedio);
		model.addAttribute("listResultados", listResultados);
		model.addAttribute("listAreas", listAreasFiltradas);
		return "generalidades";		
	}

	@RequestMapping("/results_area/{id1}")
	public String viewAreasFiltro2(Model model, @ModelAttribute("area") Area area, @PathVariable(name = "id1") String id1) {
		
		List<Area> listAreas = serviceArea.listAll();
		List<Estudiante> listEstudiantes = serviceEst.listAll();
		List<Area> listAreasFiltradas = new ArrayList<Area>();
		List<Resultado> listResultados = new ArrayList<Resultado>();
		String texto = "Primer area seleccionada: "+id1;
		for(Area a : listAreas)
		{
			String año1 = "";
			String año2 = "";
			String valor1 = "";
			String valor2 = "";
			String nombre1 = "";
			String nombre2 = "";

			if(area.getAno() != null)
			{
				año1 = a.getAno().toString();
				año2 = area.getAno().toString();
			}
			if(area.getValor() != null)
			{
				valor1 = a.getValor().toString();
				valor2 = area.getValor().toString();
			}
			if(!area.getNombre().equals(""))
			{
				nombre1 = a.getNombre();
				nombre2 = area.getNombre();
			}
			if(año1.equals(año2) && valor1.equals(valor2) && nombre1.equals(nombre2))
			{
				listAreasFiltradas.add(a);
			}
		}

		for(Estudiante e : listEstudiantes)
		{
			for(Area a : listAreasFiltradas)
			{
				if(e.getId() == a.getIdestudiante())
				{
					Resultado r = new Resultado(a.getId(), a.getNombre(), a.getValor(), a.getIdestudiante(), a.getAno(),
					 e.getId(), e.getNombre1(), e.getNombre2(), e.getApellido1(), e.getApellido2(), e.getIdentificacion(), e.getTipoid());
					 listResultados.add(r);
				}
			}
		}
		Area arean = new Area();
		Estudiante estudiante = new Estudiante();
		model.addAttribute("texto", texto);
		model.addAttribute("id", id1);
		model.addAttribute("estudiante", estudiante);
		model.addAttribute("area", arean);
		model.addAttribute("listResultados", listResultados);
		model.addAttribute("listAreas", listAreasFiltradas);
		return "results_area_compare";		
	}

	@RequestMapping("/results_estudiante")
	public String viewEstudiantesFiltro(Model model, @ModelAttribute("estudiante") Estudiante estudiante) {
		
		List<Area> listAreas = serviceArea.listAll();
		List<Estudiante> listEstudiantes = serviceEst.listAll();
		List<Estudiante> listEstudiantesFiltrados = new ArrayList<Estudiante>();
		List<Resultado> listResultados = new ArrayList<Resultado>();

		String texto = "";

		for(Estudiante e : listEstudiantes)
		{
			String id1 = "";
			String id2 = "";
			String nombre11 = "";
			String nombre12 = "";
			String nombre21 = "";
			String nombre22 = "";
			String apellido11 = "";
			String apellido12 = "";
			String apellido21 = "";
			String apellido22 = "";

			if(estudiante.getIdentificacion() != null)
			{
				id1 = e.getIdentificacion().toString();
				id2 = estudiante.getIdentificacion().toString();
			}
			if(!estudiante.getNombre1().equals(""))
			{
				nombre11 = e.getNombre1();
				nombre12 = estudiante.getNombre1();
			}
			if(!estudiante.getNombre2().equals(""))
			{
				nombre21 = e.getNombre2();
				nombre22 = estudiante.getNombre2();
			}
			if(!estudiante.getApellido1().equals(""))
			{
				apellido11 = e.getApellido1();
				apellido12 = estudiante.getApellido1();
			}
			if(!estudiante.getApellido2().equals(""))
			{
				apellido21 = e.getApellido2();
				apellido22 = estudiante.getApellido2();
			}

			texto = "Nombre : "+nombre12+" "+nombre22+" "+apellido12+" "+apellido22+"\n"+"Identificacion: "+id2;
 
			if(id1.equals(id2) && nombre11.equals(nombre12) && nombre21.equals(nombre22) &&
			apellido11.equals(apellido12) && apellido21.equals(apellido22))
			{
				listEstudiantesFiltrados.add(e);
			}
		}

		for(Estudiante e : listEstudiantesFiltrados)
		{
			for(Area a : listAreas)
			{
				if(e.getId() == a.getIdestudiante())
				{
					Resultado r = new Resultado(a.getId(), a.getNombre(), a.getValor(), a.getIdestudiante(), a.getAno(),
					 e.getId(), e.getNombre1(), e.getNombre2(), e.getApellido1(), e.getApellido2(), e.getIdentificacion(), e.getTipoid());
					 listResultados.add(r);
				}
			}
		}
		model.addAttribute("texto", texto);
		model.addAttribute("listEstudiantes", listEstudiantesFiltrados);
		model.addAttribute("listResultados", listResultados);
		return "results_estudiante";		
	}

	@RequestMapping("/results_estudiante/{id3}")
	public String viewEstudiantesFiltro2(Model model, @ModelAttribute("estudiante") Estudiante estudiante, @PathVariable(name = "id3") String id3) {
		List<Area> listAreas = serviceArea.listAll();
		List<Estudiante> listEstudiantes = serviceEst.listAll();
		List<Estudiante> listEstudiantesFiltrados = new ArrayList<Estudiante>();
		List<Resultado> listResultados = new ArrayList<Resultado>();
		for(Estudiante e : listEstudiantes)
		{
			String id1 = "0";
			String id2 = "0";
			String nombre11 = "0";
			String nombre12 = "0";
			String nombre21 = "0";
			String nombre22 = "0";
			String apellido11 = "0";
			String apellido12 = "0";
			String apellido21 = "0";
			String apellido22 = "0";

			if(estudiante.getIdentificacion() != null)
			{
				id1 = e.getIdentificacion().toString();
				id2 = estudiante.getIdentificacion().toString();
			}
			if(!estudiante.getNombre1().equals(""))
			{
				nombre11 = e.getNombre1();
				nombre12 = estudiante.getNombre1();
			}
			if(!estudiante.getNombre2().equals(""))
			{
				nombre21 = e.getNombre2();
				nombre22 = estudiante.getNombre2();
			}
			if(!estudiante.getApellido1().equals(""))
			{
				apellido11 = e.getApellido1();
				apellido12 = estudiante.getApellido1();
			}
			if(!estudiante.getApellido2().equals(""))
			{
				apellido21 = e.getApellido2();
				apellido22 = estudiante.getApellido2();
			}

			if(id1.equals(id2) && nombre11.equals(nombre12) && nombre21.equals(nombre22) &&
			apellido11.equals(apellido12) && apellido21.equals(apellido22))
			{
				listEstudiantesFiltrados.add(e);
			}
		}

		for(Estudiante e : listEstudiantesFiltrados)
		{
			for(Area a : listAreas)
			{
				if(e.getId() == a.getIdestudiante())
				{
					Resultado r = new Resultado(a.getId(), a.getNombre(), a.getValor(), a.getIdestudiante(), a.getAno(),
					 e.getId(), e.getNombre1(), e.getNombre2(), e.getApellido1(), e.getApellido2(), e.getIdentificacion(), e.getTipoid());
					 listResultados.add(r);
				}
			}
		}

		String texto = "Primer estudiante seleccionado: "+
		listResultados.get(0).getNombre1()+" "+
		listResultados.get(0).getNombre2()+" "+
		listResultados.get(0).getApellido1()+" "+
		listResultados.get(0).getApellido2()+" | Id: "+
		listResultados.get(0).getIdentificacion();

		Area arean = new Area();
		Estudiante estudianten = new Estudiante();

		model.addAttribute("texto", texto);
		model.addAttribute("id", id3);
		model.addAttribute("estudiante", estudianten);
		model.addAttribute("area", arean);
		model.addAttribute("listEstudiantes", listEstudiantesFiltrados);
		model.addAttribute("listResultados", listResultados);
		return "results_estudiante_compare";
	}

	@RequestMapping("/compare/{id}")
	public String viewEstudiantesCompare(Model model, @PathVariable(name = "id") int id) {
		List<Estudiante> listEstudiantes = serviceEst.listAll();
		List<Area> listAreas = serviceArea.listAll();
		List<Resultado> listResultados = new ArrayList<Resultado>();

		Resultado resultado = new Resultado();
		Area area = new Area();
		Estudiante estudiante = new Estudiante();

		for(Estudiante e : listEstudiantes)
		{
			for(Area a : listAreas)
			{
				if(e.getId() == a.getIdestudiante())
				{
					Resultado r = new Resultado(a.getId(), a.getNombre(), a.getValor(), a.getIdestudiante(), a.getAno(),
					 e.getId(), e.getNombre1(), e.getNombre2(), e.getApellido1(), e.getApellido2(), e.getIdentificacion(), e.getTipoid());
					 listResultados.add(r);
				}
			}
		}

		String texto = "Primer estudiante seleccionado: "+
		listResultados.get(0).getNombre1()+" "+
		listResultados.get(0).getNombre2()+" "+
		listResultados.get(0).getApellido1()+" "+
		listResultados.get(0).getApellido2()+" | Id: "+
		listResultados.get(0).getIdentificacion();

		model.addAttribute("texto", texto);
		model.addAttribute("id", id);
		model.addAttribute("listResultados", listResultados);
		model.addAttribute("resultado", resultado);
		model.addAttribute("area", area);
		model.addAttribute("estudiante", estudiante);

		return "results_estudiante_compare";		
	}

	
	@RequestMapping("/compare2/{id1}/{id2}")
	public String viewEstudiantesCompare2(Model model, @PathVariable(name = "id1") int id1, @PathVariable(name = "id2") int id2) {
		List<Estudiante> listEstudiantes = serviceEst.listAll();
		List<Estudiante> listEstudiantesComparar1 = new ArrayList<Estudiante>();
		List<Estudiante> listEstudiantesComparar2 = new ArrayList<Estudiante>();
		List<Area> listAreas = serviceArea.listAll();
		List<Resultado> listResultados1 = new ArrayList<Resultado>();
		List<Resultado> listResultados2 = new ArrayList<Resultado>();

		Resultado resultado = new Resultado();
		Area area = new Area();
		Estudiante estudiante = new Estudiante();

		for(Estudiante e : listEstudiantes)
		{
			if(e.getId().toString().equals(String.valueOf(id1)))
			{
				listEstudiantesComparar1.add(e);
			}
		}
		for(Estudiante e : listEstudiantes)
		{
			if(e.getId().toString().equals(String.valueOf(id2)))
			{
				listEstudiantesComparar2.add(e);
			}
		}
		Double prom1 = 0.0;
		int cont1 = 0;

		Double prom2 = 0.0;
		int cont2 = 0;
		for(Estudiante e : listEstudiantesComparar1)
		{
			for(Area a : listAreas)
			{
				if(e.getId() == a.getIdestudiante())
				{
					Resultado r = new Resultado(a.getId(), a.getNombre(), a.getValor(), a.getIdestudiante(), a.getAno(),
					 e.getId(), e.getNombre1(), e.getNombre2(), e.getApellido1(), e.getApellido2(), e.getIdentificacion(), e.getTipoid());
					 listResultados1.add(r);

					 prom1 += a.getValor();
					 cont1 ++;
				}
			}
		}
		for(Estudiante e : listEstudiantesComparar2)
		{
			for(Area a : listAreas)
			{
				if(e.getId() == a.getIdestudiante())
				{
					Resultado r = new Resultado(a.getId(), a.getNombre(), a.getValor(), a.getIdestudiante(), a.getAno(),
					 e.getId(), e.getNombre1(), e.getNombre2(), e.getApellido1(), e.getApellido2(), e.getIdentificacion(), e.getTipoid());
					 listResultados2.add(r);

					 prom2 += a.getValor();
					 cont2 ++;
				}
			}
		}

		prom1 = prom1/cont1;
		prom1 = round(prom1,2);
		prom2 = prom2/cont2;
		prom2 = round(prom2,2);

		String text1 = listEstudiantesComparar1.get(0).getNombre1()+" "+
		listEstudiantesComparar1.get(0).getNombre2()+" "+
		listEstudiantesComparar1.get(0).getApellido1()+" "+
		listEstudiantesComparar1.get(0).getApellido2()+" - "+
		listEstudiantesComparar1.get(0).getIdentificacion().toString()+
		" | Promedio: "+prom1;

		String text2 = listEstudiantesComparar2.get(0).getNombre1()+" "+
		listEstudiantesComparar2.get(0).getNombre2()+" "+
		listEstudiantesComparar2.get(0).getApellido1()+" "+
		listEstudiantesComparar2.get(0).getApellido2()+" - "+
		listEstudiantesComparar2.get(0).getIdentificacion().toString()+
		" | Promedio: "+prom2;

		model.addAttribute("text1", text1);
		model.addAttribute("text2", text2);
		model.addAttribute("listResultados1", listResultados1);
		model.addAttribute("listResultados2", listResultados2);
		model.addAttribute("resultado", resultado);
		model.addAttribute("area", area);
		model.addAttribute("estudiante", estudiante);

		return "results_compare";		
	}

	@RequestMapping("/compare/area/{id}")
	public String viewAreasCompare(Model model, @PathVariable(name = "id") String id) {
		List<Estudiante> listEstudiantes = serviceEst.listAll();
		List<Area> listAreas = serviceArea.listAll();
		List<Resultado> listResultados = new ArrayList<Resultado>();

		Resultado resultado = new Resultado();
		Area area = new Area();
		Estudiante estudiante = new Estudiante();

		String texto = "Primer area seleccionada: "+id;

		for(Estudiante e : listEstudiantes)
		{
			for(Area a : listAreas)
			{
				if(e.getId() == a.getIdestudiante())
				{
					Resultado r = new Resultado(a.getId(), a.getNombre(), a.getValor(), a.getIdestudiante(), a.getAno(),
					 e.getId(), e.getNombre1(), e.getNombre2(), e.getApellido1(), e.getApellido2(), e.getIdentificacion(), e.getTipoid());
					 listResultados.add(r);
				}
			}
		}
		model.addAttribute("texto", texto);
		model.addAttribute("id", id);
		model.addAttribute("listResultados", listResultados);
		model.addAttribute("listAreas", listAreas);
		model.addAttribute("resultado", resultado);
		model.addAttribute("area", area);
		model.addAttribute("estudiante", estudiante);

		return "results_area_compare";		
	}

	@RequestMapping("/compare2/area/{id1}/{id2}")
	public String viewAreaCompare2(Model model, @PathVariable(name = "id1") String id1, @PathVariable(name = "id2") String id2) {
		List<Estudiante> listEstudiantes = serviceEst.listAll();
		List<Area> listAreasComparar1 = new ArrayList<Area>();
		List<Area> listAreasComparar2 = new ArrayList<Area>();
		List<Area> listAreas = serviceArea.listAll();
		List<Resultado> listResultados1 = new ArrayList<Resultado>();
		List<Resultado> listResultados2 = new ArrayList<Resultado>();

		Resultado resultado = new Resultado();
		Area area = new Area();
		Estudiante estudiante = new Estudiante();

		for(Area a : listAreas)
		{
			if(a.getNombre().equals(id1))
			{
				listAreasComparar1.add(a);
			}
		}
		for(Area a : listAreas)
		{
			if(a.getNombre().equals(id2))
			{
				listAreasComparar2.add(a);
			}
		}
		Double prom1 = 0.0;
		int cont1 = 0;
		Double prom2 = 0.0;
		int cont2 = 0;
		for(Estudiante e : listEstudiantes)
		{
			for(Area a : listAreasComparar1)
			{
				if(e.getId() == a.getIdestudiante())
				{
					Resultado r = new Resultado(a.getId(), a.getNombre(), a.getValor(), a.getIdestudiante(), a.getAno(),
					 e.getId(), e.getNombre1(), e.getNombre2(), e.getApellido1(), e.getApellido2(), e.getIdentificacion(), e.getTipoid());
					 listResultados1.add(r);
					 prom1 += a.getValor();
					 cont1++;
				}
			}
		}
		prom1 = prom1/cont1;
		prom1 = round(prom1, 2);
		for(Estudiante e : listEstudiantes)
		{
			for(Area a : listAreasComparar2)
			{
				if(e.getId() == a.getIdestudiante())
				{
					Resultado r = new Resultado(a.getId(), a.getNombre(), a.getValor(), a.getIdestudiante(), a.getAno(),
					 e.getId(), e.getNombre1(), e.getNombre2(), e.getApellido1(), e.getApellido2(), e.getIdentificacion(), e.getTipoid());
					 listResultados2.add(r);
					 prom2 += a.getValor();
					 cont2++;
				}
			}
		}
		prom2 = prom2/cont2;
		prom2 = round(prom2, 2);
		String text1 = "Area: "+listAreasComparar1.get(0).getNombre()+" "+
		" Identificacion: "+listAreasComparar1.get(0).getId().toString()+" "+
		" Promedio :"+prom1;

		String text2 = "Area: "+listAreasComparar2.get(0).getNombre()+" "+
		" Identificacion: "+listAreasComparar2.get(0).getId().toString()+" "+
		" Promedio :"+prom2;

		model.addAttribute("text1", text1);
		model.addAttribute("text2", text2);
		model.addAttribute("listResultados1", listResultados1);
		model.addAttribute("listResultados2", listResultados2);
		model.addAttribute("resultado", resultado);
		model.addAttribute("area", area);
		model.addAttribute("estudiante", estudiante);

		return "results_compare_area";		
	}

	public static double round(double value, int places) {
		if (places < 0) throw new IllegalArgumentException();
	
		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}
}
