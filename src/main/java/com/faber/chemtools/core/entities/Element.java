package com.faber.chemtools.core.entities;

import java.util.HashMap;
import java.util.Map;

public enum Element {

    H(1, "H", "Hydrogen"),
    He(2, "He", "Helium"),
    Li(3, "Li", "Lithium"),
    Be(4, "Be", "Beryllium"),
    B(5, "B", "Boron"),
    C(6, "C", "Carbon"),
    N(7, "N", "Nitrogen"),
    O(8, "O", "Oxygen"),
    F(9, "F", "Fluorine"),
    Ne(10, "Ne", "Neon"),
    Na(11, "Na", "Sodium"),
    Mg(12, "Mg", "Magnesium"),
    Al(13, "Al", "Aluminium"),
    Si(14, "Si", "Silicon"),
    P(15, "P", "Phosphorus"),
    S(16, "S", "Sulfur"),
    Cl(17, "Cl", "Chlorine"),
    Ar(18, "Ar", "Argon"),
    K(19, "K", "Potassium"),
    Ca(20, "Ca", "Calcium"),
    Sc(21, "Sc", "Scandium"),
    Ti(22, "Ti", "Titanium"),
    V(23, "V", "Vanadium"),
    Cr(24, "Cr", "Chromium"),
    Mn(25, "Mn", "Manganese"),
    Fe(26, "Fe", "Iron"),
    Co(27, "Co", "Cobalt"),
    Ni(28, "Ni", "Nickel"),
    Cu(29, "Cu", "Copper"),
    Zn(30, "Zn", "Zinc"),
    Ga(31, "Ga", "Gallium"),
    Ge(32, "Ge", "Germanium"),
    As(33, "As", "Arsenic"),
    Se(34, "Se", "Selenium"),
    Br(35, "Br", "Bromine"),
    Kr(36, "Kr", "Krypton"),
    Rb(37, "Rb", "Rubidium"),
    Sr(38, "Sr", "Strontium"),
    Y(39, "Y", "Yttrium"),
    Zr(40, "Zr", "Zirconium"),
    Nb(41, "Nb", "Niobium"),
    Mo(42, "Mo", "Molybdenum"),
    Tc(43, "Tc", "Technetium"),
    Ru(44, "Ru", "Ruthenium"),
    Rh(45, "Rh", "Rhodium"),
    Pd(46, "Pd", "Palladium"),
    Ag(47, "Ag", "Silver"),
    Cd(48, "Cd", "Cadmium"),
    In(49, "In", "Indium"),
    Sn(50, "Sn", "Tin"),
    Sb(51, "Sb", "Antimony"),
    Te(52, "Te", "Tellurium"),
    I(53, "I", "Iodine"),
    Xe(54, "Xe", "Xenon"),
    Cs(55, "Cs", "Cesium"),
    Ba(56, "Ba", "Barium"),
    La(57, "La", "Lanthanum"),
    Ce(58, "Ce", "Cerium"),
    Pr(59, "Pr", "Praseodymium"),
    Nd(60, "Nd", "Neodymium"),
    Pm(61, "Pm", "Promethium"),
    Sm(62, "Sm", "Samarium"),
    Eu(63, "Eu", "Europium"),
    Gd(64, "Gd", "Gadolinium"),
    Tb(65, "Tb", "Terbium"),
    Dy(66, "Dy", "Dysprosium"),
    Ho(67, "Ho", "Holmium"),
    Er(68, "Er", "Erbium"),
    Tm(69, "Tm", "Thulium"),
    Yb(70, "Yb", "Ytterbium"),
    Lu(71, "Lu", "Lutetium"),
    Hf(72, "Hf", "Hafnium"),
    Ta(73, "Ta", "Tantalum"),
    W(74, "W", "Tungsten"),
    Re(75, "Re", "Rhenium"),
    Os(76, "Os", "Osmium"),
    Ir(77, "Ir", "Iridium"),
    Pt(78, "Pt", "Platinum"),
    Au(79, "Au", "Gold"),
    Hg(80, "Hg", "Mercury"),
    Tl(81, "Tl", "Thallium"),
    Pb(82, "Pb", "Lead"),
    Bi(83, "Bi", "Bismuth"),
    Po(84, "Po", "Polonium"),
    At(85, "At", "Astatine"),
    Rn(86, "Rn", "Radon"),
    Fr(87, "Fr", "Francium"),
    Ra(88, "Ra", "Radium"),
    Ac(89, "Ac", "Actinium"),
    Th(90, "Th", "Thorium"),
    Pa(91, "Pa", "Protactinium"),
    U(92, "U", "Uranium"),
    Np(93, "Np", "Neptunium"),
    Pu(94, "Pu", "Plutonium"),
    Am(95, "Am", "Americium"),
    Cm(96, "Cm", "Curium"),
    Bk(97, "Bk", "Berkelium"),
    Cf(98, "Cf", "Californium"),
    Es(99, "Es", "Einsteinium"),
    Fm(100, "Fm", "Fermium"),
    Md(101, "Md", "Mendelevium"),
    No(102, "No", "Nobelium"),
    Lr(103, "Lr", "Lawrencium"),
    Rf(104, "Rf", "Rutherfordium"),
    Db(105, "Db", "Dubnium"),
    Sg(106, "Sg", "Seaborgium"),
    Bh(107, "Bh", "Bohrium"),
    Hs(108, "Hs", "Hassium"),
    Mt(109, "Mt", "Meitnerium"),
    Ds(110, "Ds", "Darmstadtium"),
    Rg(111, "Rg", "Roentgenium"),
    Cn(112, "Cn", "Copernicium"),
    Nh(113, "Nh", "Nihonium"),
    Fl(114, "Fl", "Flerovium"),
    Mc(115, "Mc", "Moscovium"),
    Lv(116, "Lv", "Livermorium"),
    Ts(117, "Ts", "Tennessine"),
    Og(118, "Og", "Oganesson");

    private final int atomicNumber;
    private final String symbol;
    private final String name;

    private static final Map<String, Element> BY_SYMBOL = new HashMap<>();
    private static final Map<Integer, Element> BY_NUMBER = new HashMap<>();

    static {
        for (Element e : values()) {
            BY_SYMBOL.put(e.symbol, e);
            BY_NUMBER.put(e.atomicNumber, e);
        }
    }

    Element(int atomicNumber, String symbol, String name) {
        this.atomicNumber = atomicNumber;
        this.symbol = symbol;
        this.name = name;
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

            case Li, Na, K, Rb, Cs, Fr -> true;

            case Be, Mg, Ca, Sr, Ba, Ra -> true;

            case La, Ce, Pr, Nd, Pm, Sm, Eu, Gd, Tb, Dy, Ho, Er, Tm, Yb, Lu -> true;

            case Ac, Th, Pa, U, Np, Pu, Am, Cm, Bk, Cf, Es, Fm, Md, No, Lr -> true;

            case Sc, Ti, V, Cr, Mn, Fe, Co, Ni, Cu, Zn,
                 Y, Zr, Nb, Mo, Tc, Ru, Rh, Pd, Ag, Cd,
                 Hf, Ta, W, Re, Os, Ir, Pt, Au, Hg,
                 Rf, Db, Sg, Bh, Hs, Mt, Ds, Rg, Cn -> true;

            case Al, Ga, In, Sn, Tl, Pb, Bi, Fl, Lv -> true;

            default -> false;
        };
    }

}
