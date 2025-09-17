package com.faber.chemtools.core.molecules.business;

import com.faber.chemtools.core.exceptions.InvalidElementException;
import com.faber.chemtools.core.molecules.entities.Element;

import java.util.List;

public class PeriodicTableList {
    private static List<Element> elements;

    static {
        load();
    }

    public static Element getElement(int atomicNumber) throws InvalidElementException {
        return elements.stream()
                .filter(e -> e.getAtomicNumber() == atomicNumber)
                .findFirst()
                .orElseThrow(InvalidElementException::new);
    }

    public static Element getElement(String simbol) throws InvalidElementException {
        return elements.stream()
                .filter(e -> e.getSimbol().equals(simbol))
                .findFirst()
                .orElseThrow(InvalidElementException::new);
    }

    private static void load() {
        if (elements == null) {
            elements = List.of(
                    new Element("H", "Hydrogen", 1, 1.008),
                    new Element("He", "Helium", 2, 4.0026),
                    new Element("Li", "Lithium", 3, 6.94),
                    new Element("Be", "Beryllium", 4, 9.0122),
                    new Element("B", "Boron", 5, 10.81),
                    new Element("C", "Carbon", 6, 12.011),
                    new Element("N", "Nitrogen", 7, 14.007),
                    new Element("O", "Oxygen", 8, 15.999),
                    new Element("F", "Fluorine", 9, 18.998),
                    new Element("Ne", "Neon", 10, 20.180),
                    new Element("Na", "Sodium", 11, 22.990),
                    new Element("Mg", "Magnesium", 12, 24.305),
                    new Element("Al", "Aluminium", 13, 26.982),
                    new Element("Si", "Silicon", 14, 28.085),
                    new Element("P", "Phosphorus", 15, 30.974),
                    new Element("S", "Sulfur", 16, 32.06),
                    new Element("Cl", "Chlorine", 17, 35.45),
                    new Element("Ar", "Argon", 18, 39.948),
                    new Element("K", "Potassium", 19, 39.098),
                    new Element("Ca", "Calcium", 20, 40.078),
                    new Element("Sc", "Scandium", 21, 44.956),
                    new Element("Ti", "Titanium", 22, 47.867),
                    new Element("V", "Vanadium", 23, 50.942),
                    new Element("Cr", "Chromium", 24, 52.00),
                    new Element("Mn", "Manganese", 25, 54.938),
                    new Element("Fe", "Iron", 26, 55.845),
                    new Element("Co", "Cobalt", 27, 58.933),
                    new Element("Ni", "Nickel", 28, 58.693),
                    new Element("Cu", "Copper", 29, 63.546),
                    new Element("Zn", "Zinc", 30, 65.38),
                    new Element("Ga", "Gallium", 31, 69.723),
                    new Element("Ge", "Germanium", 32, 72.63),
                    new Element("As", "Arsenic", 33, 74.922),
                    new Element("Se", "Selenium", 34, 78.971),
                    new Element("Br", "Bromine", 35, 79.904),
                    new Element("Kr", "Krypton", 36, 83.798),
                    new Element("Rb", "Rubidium", 37, 85.468),
                    new Element("Sr", "Strontium", 38, 87.62),
                    new Element("Y", "Yttrium", 39, 88.906),
                    new Element("Zr", "Zirconium", 40, 91.224),
                    new Element("Nb", "Niobium", 41, 92.906),
                    new Element("Mo", "Molybdenum", 42, 95.95),
                    new Element("Tc", "Technetium", 43, 98),
                    new Element("Ru", "Ruthenium", 44, 101.07),
                    new Element("Rh", "Rhodium", 45, 102.905),
                    new Element("Pd", "Palladium", 46, 106.42),
                    new Element("Ag", "Silver", 47, 107.868),
                    new Element("Cd", "Cadmium", 48, 112.414),
                    new Element("In", "Indium", 49, 114.818),
                    new Element("Sn", "Tin", 50, 118.710),
                    new Element("Sb", "Antimony", 51, 121.760),
                    new Element("Te", "Tellurium", 52, 127.60),
                    new Element("I", "Iodine", 53, 126.904),
                    new Element("Xe", "Xenon", 54, 131.293),
                    new Element("Cs", "Caesium", 55, 132.905),
                    new Element("Ba", "Barium", 56, 137.327),
                    new Element("La", "Lanthanum", 57, 138.905),
                    new Element("Ce", "Cerium", 58, 140.116),
                    new Element("Pr", "Praseodymium", 59, 140.907),
                    new Element("Nd", "Neodymium", 60, 144.242),
                    new Element("Pm", "Promethium", 61, 145),
                    new Element("Sm", "Samarium", 62, 150.36),
                    new Element("Eu", "Europium", 63, 151.984),
                    new Element("Gd", "Gadolinium", 64, 157.25),
                    new Element("Tb", "Terbium", 65, 158.925),
                    new Element("Dy", "Dysprosium", 66, 162.500),
                    new Element("Ho", "Holmium", 67, 164.930),
                    new Element("Er", "Erbium", 68, 167.259),
                    new Element("Tm", "Thulium", 69, 168.934),
                    new Element("Yb", "Ytterbium", 70, 173.04),
                    new Element("Lu", "Lutetium", 71, 174.966),
                    new Element("Hf", "Hafnium", 72, 178.49),
                    new Element("Ta", "Tantalum", 73, 180.948),
                    new Element("W", "Tungsten", 74, 183.84),
                    new Element("Re", "Rhenium", 75, 186.207),
                    new Element("Os", "Osmium", 76, 190.23),
                    new Element("Ir", "Iridium", 77, 192.217),
                    new Element("Pt", "Platinum", 78, 195.084),
                    new Element("Au", "Gold", 79, 196.967),
                    new Element("Hg", "Mercury", 80, 200.592),
                    new Element("Tl", "Thallium", 81, 204.38),
                    new Element("Pb", "Lead", 82, 207.2),
                    new Element("Bi", "Bismuth", 83, 208.980),
                    new Element("Po", "Polonium", 84, 209),
                    new Element("At", "Astatine", 85, 210),
                    new Element("Rn", "Radon", 86, 222),
                    new Element("Fr", "Francium", 87, 223),
                    new Element("Ra", "Radium", 88, 226),
                    new Element("Ac", "Actinium", 89, 227),
                    new Element("Th", "Thorium", 90, 232.037),
                    new Element("Pa", "Protactinium", 91, 231.035),
                    new Element("U", "Uranium", 92, 238.028),
                    new Element("Np", "Neptunium", 93, 237),
                    new Element("Pu", "Plutonium", 94, 244),
                    new Element("Am", "Americium", 95, 243),
                    new Element("Cm", "Curium", 96, 247),
                    new Element("Bk", "Berkelium", 97, 247),
                    new Element("Cf", "Californium", 98, 251),
                    new Element("Es", "Einsteinium", 99, 252),
                    new Element("Fm", "Fermium", 100, 257),
                    new Element("Md", "Mendelevium", 101, 258),
                    new Element("No", "Nobelium", 102, 259),
                    new Element("Lr", "Lawrencium", 103, 262),
                    new Element("Rf", "Rutherfordium", 104, 267),
                    new Element("Db", "Dubnium", 105, 270),
                    new Element("Sg", "Seaborgium", 106, 271),
                    new Element("Bh", "Bohrium", 107, 270),
                    new Element("Hs", "Hassium", 108, 277),
                    new Element("Mt", "Meitnerium", 109, 278),
                    new Element("Ds", "Darmstadtium", 110, 281),
                    new Element("Rg", "Roentgenium", 111, 280),
                    new Element("Cn", "Copernicium", 112, 285),
                    new Element("Nh", "Nihonium", 113, 284),
                    new Element("Fl", "Flerovium", 114, 289),
                    new Element("Mc", "Moscovium", 115, 288),
                    new Element("Lv", "Livermorium", 116, 293),
                    new Element("Ts", "Tennessine", 117, 294),
                    new Element("Og", "Oganesson", 118, 294)
            );
        }
    }
}

