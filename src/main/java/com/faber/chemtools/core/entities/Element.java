package com.faber.chemtools.core.entities;

import java.util.HashMap;
import java.util.Map;

public enum Element {

    H (1, "H", "Hydrogen", 1.008),
    He(2, "He", "Helium", 4.0026),
    Li(3, "Li", "Lithium", 6.94),
    Be(4, "Be", "Beryllium", 9.0122),
    B (5, "B",  "Boron", 10.81),
    C (6, "C",  "Carbon", 12.011),
    N (7, "N",  "Nitrogen", 14.007),
    O (8, "O",  "Oxygen", 15.999),
    F (9, "F",  "Fluorine", 18.998),
    Ne(10, "Ne","Neon", 20.180),
    Na(11, "Na","Sodium", 22.990),
    Mg(12, "Mg","Magnesium", 24.305),
    Al(13, "Al","Aluminium", 26.982),
    Si(14, "Si","Silicon", 28.085),
    P (15, "P", "Phosphorus", 30.974),
    S (16, "S", "Sulfur", 32.06),
    Cl(17, "Cl","Chlorine", 35.45),
    Ar(18, "Ar","Argon", 39.948),
    K (19, "K", "Potassium", 39.098),
    Ca(20, "Ca","Calcium", 40.078),
    Sc(21, "Sc","Scandium", 44.956),
    Ti(22, "Ti","Titanium", 47.867),
    V (23, "V", "Vanadium", 50.942),
    Cr(24, "Cr","Chromium", 51.996),
    Mn(25, "Mn","Manganese", 54.938),
    Fe(26, "Fe","Iron", 55.845),
    Co(27, "Co","Cobalt", 58.933),
    Ni(28, "Ni","Nickel", 58.693),
    Cu(29, "Cu","Copper", 63.546),
    Zn(30, "Zn","Zinc", 65.38),
    Ga(31, "Ga","Gallium", 69.723),
    Ge(32, "Ge","Germanium", 72.630),
    As(33, "As","Arsenic", 74.922),
    Se(34, "Se","Selenium", 78.971),
    Br(35, "Br","Bromine", 79.904),
    Kr(36, "Kr","Krypton", 83.798),
    Rb(37, "Rb","Rubidium", 85.468),
    Sr(38, "Sr","Strontium", 87.62),
    Y (39, "Y", "Yttrium", 88.906),
    Zr(40, "Zr","Zirconium", 91.224),
    Nb(41, "Nb","Niobium", 92.906),
    Mo(42, "Mo","Molybdenum", 95.95),
    Tc(43, "Tc","Technetium", 98),
    Ru(44, "Ru","Ruthenium", 101.07),
    Rh(45, "Rh","Rhodium", 102.91),
    Pd(46, "Pd","Palladium", 106.42),
    Ag(47, "Ag","Silver", 107.87),
    Cd(48, "Cd","Cadmium", 112.41),
    In(49, "In","Indium", 114.82),
    Sn(50, "Sn","Tin", 118.71),
    Sb(51, "Sb","Antimony", 121.76),
    Te(52, "Te","Tellurium", 127.60),
    I (53, "I", "Iodine", 126.90),
    Xe(54, "Xe","Xenon", 131.29),
    Cs(55, "Cs","Cesium", 132.91),
    Ba(56, "Ba","Barium", 137.33),
    La(57, "La","Lanthanum", 138.91),
    Ce(58, "Ce","Cerium", 140.12),
    Pr(59, "Pr","Praseodymium", 140.91),
    Nd(60, "Nd","Neodymium", 144.24),
    Pm(61, "Pm","Promethium", 145),
    Sm(62, "Sm","Samarium", 150.36),
    Eu(63, "Eu","Europium", 151.96),
    Gd(64, "Gd","Gadolinium", 157.25),
    Tb(65, "Tb","Terbium", 158.93),
    Dy(66, "Dy","Dysprosium", 162.50),
    Ho(67, "Ho","Holmium", 164.93),
    Er(68, "Er","Erbium", 167.26),
    Tm(69, "Tm","Thulium", 168.93),
    Yb(70, "Yb","Ytterbium", 173.05),
    Lu(71, "Lu","Lutetium", 174.97),
    Hf(72, "Hf","Hafnium", 178.49),
    Ta(73, "Ta","Tantalum", 180.95),
    W (74, "W", "Tungsten", 183.84),
    Re(75, "Re","Rhenium", 186.21),
    Os(76, "Os","Osmium", 190.23),
    Ir(77, "Ir","Iridium", 192.22),
    Pt(78, "Pt","Platinum", 195.08),
    Au(79, "Au","Gold", 196.97),
    Hg(80, "Hg","Mercury", 200.59),
    Tl(81, "Tl","Thallium", 204.38),
    Pb(82, "Pb","Lead", 207.2),
    Bi(83, "Bi","Bismuth", 208.98),
    Po(84, "Po","Polonium", 209),
    At(85, "At","Astatine", 210),
    Rn(86, "Rn","Radon", 222),
    Fr(87, "Fr","Francium", 223),
    Ra(88, "Ra","Radium", 226),
    Ac(89, "Ac","Actinium", 227),
    Th(90, "Th","Thorium", 232.04),
    Pa(91, "Pa","Protactinium", 231.04),
    U (92, "U", "Uranium", 238.03),
    Np(93, "Np","Neptunium", 237),
    Pu(94, "Pu","Plutonium", 244),
    Am(95, "Am","Americium", 243),
    Cm(96, "Cm","Curium", 247),
    Bk(97, "Bk","Berkelium", 247),
    Cf(98, "Cf","Californium", 251),
    Es(99, "Es","Einsteinium", 252),
    Fm(100,"Fm","Fermium", 257),
    Md(101,"Md","Mendelevium", 258),
    No(102,"No","Nobelium", 259),
    Lr(103,"Lr","Lawrencium", 266),
    Rf(104,"Rf","Rutherfordium", 267),
    Db(105,"Db","Dubnium", 268),
    Sg(106,"Sg","Seaborgium", 269),
    Bh(107,"Bh","Bohrium", 270),
    Hs(108,"Hs","Hassium", 270),
    Mt(109,"Mt","Meitnerium", 278),
    Ds(110,"Ds","Darmstadtium", 281),
    Rg(111,"Rg","Roentgenium", 282),
    Cn(112,"Cn","Copernicium", 285),
    Nh(113,"Nh","Nihonium", 286),
    Fl(114,"Fl","Flerovium", 289),
    Mc(115,"Mc","Moscovium", 290),
    Lv(116,"Lv","Livermorium", 293),
    Ts(117,"Ts","Tennessine", 294),
    Og(118,"Og","Oganesson", 294);

    private final int atomicNumber;
    private final String symbol;
    private final String name;
    private final double atomicWeight;

    private static final Map<String, Element> BY_SYMBOL = new HashMap<>();
    private static final Map<Integer, Element> BY_NUMBER = new HashMap<>();

    static {
        for (Element e : values()) {
            BY_SYMBOL.put(e.symbol, e);
            BY_NUMBER.put(e.atomicNumber, e);
        }
    }

    Element(int atomicNumber, String symbol, String name, double atomicWeight) {
        this.atomicNumber = atomicNumber;
        this.symbol = symbol;
        this.name = name;
        this.atomicWeight = atomicWeight;
    }

    public int getAtomicNumber() {
        return atomicNumber;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public double getAtomicWeight() {
        return atomicWeight;
    }

    public static Element fromSymbol(String symbol) {
        Element e = BY_SYMBOL.get(symbol);
        if (e == null) throw new IllegalArgumentException("Unknown symbol: " + symbol);
        return e;
    }

    public static Element fromAtomicNumber(int number) {
        Element e = BY_NUMBER.get(number);
        if (e == null) throw new IllegalArgumentException("Unknown atomic number: " + number);
        return e;
    }

    public boolean isMetal() {
        return switch (this) {
            case Li, Na, K, Rb, Cs, Fr,
                 Be, Mg, Ca, Sr, Ba, Ra,
                 La, Ce, Pr, Nd, Pm, Sm, Eu, Gd, Tb, Dy, Ho, Er, Tm, Yb, Lu,
                 Ac, Th, Pa, U, Np, Pu, Am, Cm, Bk, Cf, Es, Fm, Md, No, Lr,
                 Sc, Ti, V, Cr, Mn, Fe, Co, Ni, Cu, Zn,
                 Y, Zr, Nb, Mo, Tc, Ru, Rh, Pd, Ag, Cd,
                 Hf, Ta, W, Re, Os, Ir, Pt, Au, Hg,
                 Rf, Db, Sg, Bh, Hs, Mt, Ds, Rg, Cn,
                 Al, Ga, In, Sn, Tl, Pb, Bi, Fl, Lv -> true;

            default -> false;
        };
    }
}
