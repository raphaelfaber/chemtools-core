<h1>ChemTools - Java Chemistry Toolkit</h1>

<p><strong>v1.0.0-SNAPSHOT</strong></p>

<p>A Java project for <strong>extracting, analyzing, and balancing chemical equations</strong>, supporting:</p>
<ul>
    <li>Stoichiometric coefficients</li>
    <li>Formulas with parentheses (<code>Ca(OH)2</code>, <code>Ba3(PO4)2</code>)</li>
    <li>Optional physical states (<code>(l)</code>, <code>(g)</code>, <code>(s)</code>, <code>(aq)</code>)</li>
    <li>Automated testing with JUnit</li>
</ul>

<p>This project is designed for learning, automating chemistry exercises, and integrating with modern Java tools.</p>

<h2>Main Features</h2>
<ul>
    <li><strong>Equation Extraction</strong><br>Converts a chemical equation string into manipulable objects, separating reactants, products, coefficients, and molecules.</li>
    <li><strong>Automatic Balancing</strong><br>Adjusts coefficients to obey the law of conservation of mass.</li>
    <li><strong>Complex Molecule Support</strong><br>Handles compounds with internal parentheses, multiple elements, and subscripts.</li>
    <li><strong>Automated Tests</strong><br>Includes simple and complex reactions, such as redox reactions, salts, metal oxides, and aqueous compounds.</li>
    <li><strong>Amount of substance</strong><br>Automates calculations of amount of substance and molar mass.</li>
</ul>

<h2>Usage Examples</h2>
  <p class="small">Examples below are based on unit tests present in the project. Import paths reflect the project's package layout.</p>

  <h2><span class="tag">1)</span>Expand chemical formulas</h2>
  <p class="example-note">Use <code>FormulaExpander</code> to expand grouped or nested formulas into a flattened element string.</p>

  <pre><code>// imports
import com.faber.chemtools.core.util.parser.FormulaExpander;

public class ExpandExample {
    public static void main(String[] args) {
        System.out.println(FormulaExpander.expand("Ca(OH)2"));
        // Output: CaO2H2

        System.out.println(FormulaExpander.expand("K4(ON(SO3)2)2"));
        // Output: K4O14N2S4
    }
}
</code></pre>

  <h2><span class="tag">2)</span>Create molecules & build reactions</h2>
  <p class="example-note">Create <code>Molecule</code> instances by adding elements (using <code>ElementData</code>) then compose a <code>Reaction</code>.</p>

  <pre><code>// imports
import com.faber.chemtools.core.elements.business.ElementData;
import com.faber.chemtools.core.elements.entities.Element;
import com.faber.chemtools.core.molecules.entities.Molecule;
import com.faber.chemtools.core.reactions.entities.Reaction;

public class MoleculeReactionExample {
    public static void main(String[] args) throws Exception {
        Element H = ElementData.getElement(1);  // Hydrogen
        Element O = ElementData.getElement(8);  // Oxygen

        Molecule H2 = new Molecule();
        H2.addElement(H, 2);

        Molecule O2 = new Molecule();
        O2.addElement(O, 2);

        Molecule H2O = new Molecule();
        H2O.addElement(H, 2);
        H2O.addElement(O, 1);

        Reaction reaction = new Reaction();
        reaction.addMultipleReagents(H2, O2);
        reaction.addMultipleProducts(H2O);

        System.out.println(reaction);
        // Example output: H2 + O2 -> H2O
    }
}
</code></pre>

  <h2><span class="tag">3)</span>Balance reactions</h2>
  <p class="example-note">Use <code>BalanceEquation</code> to balance a constructed reaction programmatically.</p>

  <pre><code>// imports
import com.faber.chemtools.core.reactions.business.BalanceEquation;
import com.faber.chemtools.core.reactions.entities.Reaction;

// assume 'reaction' created as above
Reaction balanced = BalanceEquation.balance(reaction);
System.out.println(balanced);
// Example output: 2 H2 + O2 -> 2 H2O  (depending on input sides)
</code></pre>

  <h2><span class="tag">4)</span>Calculate molecular weight</h2>
  <p class="example-note">Given a <code>Molecule</code> instance, compute its molecular mass using <code>FromMolecule.calculateMolecularWeight</code>.</p>

  <pre><code>// imports
import com.faber.chemtools.core.molecules.business.FromMolecule;
import com.faber.chemtools.core.elements.business.ElementData;
import com.faber.chemtools.core.molecules.entities.Molecule;

public class MolecularWeightExample {
    public static void main(String[] args) throws Exception {
        Molecule glucose = new Molecule();
        glucose.addElement(ElementData.getElement(6), 6); // C
        glucose.addElement(ElementData.getElement(1), 12); // H
        glucose.addElement(ElementData.getElement(8), 6); // O

        double mass = FromMolecule.calculateMolecularWeight(glucose);
        System.out.println("Glucose Molar Mass ≈ " + mass + " g/mol");
        // Expected: ≈ 180.156 g/mol
    }
}
</code></pre>

  <h2><span class="tag">5)</span>Calculate amount of substance (moles)</h2>
  <p class="example-note">Use <code>FromMolecule.calculateAmountOfSubstance</code> to convert grams to moles.</p>

  <pre><code>// imports
import com.faber.chemtools.core.molecules.business.FromMolecule;
import com.faber.chemtools.core.elements.business.ElementData;
import com.faber.chemtools.core.molecules.entities.Molecule;

public class MolesExample {
    public static void main(String[] args) throws Exception {
        // H2O example
        Molecule water = new Molecule();
        water.addElement(ElementData.getElement(1), 2); // H
        water.addElement(ElementData.getElement(8), 1); // O

        double grams = 18.015;
        double moles = FromMolecule.calculateAmountOfSubstance(grams, water);
        System.out.println("Moles of H2O in " + grams + " g = " + moles);
        // Expected: ≈ 1.0
    }
}
</code></pre>

  <h2><span class="tag">6)</span>Extract elements from molecules/reactions</h2>
  <p class="example-note">Helpers like <code>ListElementsFrom.molecule</code> and <code>ListElementsFrom.moleculesInReaction</code> return the element list used in a molecule or reaction.</p>

  <pre><code>// imports
import com.faber.chemtools.core.elements.business.ListElementsFrom;
import com.faber.chemtools.core.molecules.entities.Molecule;
import com.faber.chemtools.core.elements.business.ElementData;

public class ElementsExtractionExample {
    public static void main(String[] args) throws Exception {
        Molecule m = new Molecule();
        m.addElement(ElementData.getElement("O"), 4);
        m.addElement(ElementData.getElement("H"), 1);
        m.addElement(ElementData.getElement("Cl"), 1);

        var elements = ListElementsFrom.molecule(m);
        elements.forEach(e -> System.out.println(e.getSimbol()));
        // Expected printed elements: O, H, Cl  (order may vary)
    }
}
</code></pre>
  
<h2>Dependencies</h2>
<ul>
    <li>Java 24+</li>
    <li>Maven or Gradle for dependency management (JUnit 5 included)</li>
</ul>

<pre><code>&lt;dependencies&gt;
    &lt;dependency&gt;
        &lt;groupId&gt;junit&lt;/groupId&gt;
        &lt;artifactId&gt;junit&lt;/artifactId&gt;
        &lt;version&gt;3.8.1&lt;/version&gt;
        &lt;scope&gt;test&lt;/scope&gt;
    &lt;/dependency&gt;

    &lt;dependency&gt;
        &lt;groupId&gt;org.junit.jupiter&lt;/groupId&gt;
        &lt;artifactId&gt;junit-jupiter&lt;/artifactId&gt;
        &lt;version&gt;5.10.0&lt;/version&gt;
        &lt;scope&gt;test&lt;/scope&gt;
    &lt;/dependency&gt;

    &lt;dependency&gt;
        &lt;groupId&gt;org.apache.commons&lt;/groupId&gt;
        &lt;artifactId&gt;commons-math3&lt;/artifactId&gt;
        &lt;version&gt;3.6.1&lt;/version&gt;
    &lt;/dependency&gt;
&lt;/dependencies&gt;
</code></pre>

<h2>Contributing</h2>
<ul>
    <li>Open issues for bugs or suggestions</li>
    <li>Pull requests are welcome for:
        <ul>
            <li>Improving the equation parser</li>
            <li>Adding balancing for organic reactions</li>
            <li>Performance optimization</li>
            <li>Support for ionic equations</li>
            <li>Implement new features</li>
        </ul>
    </li>
</ul>

<h2>License</h2>
<p>MIT License – feel free to use and modify this project.</p>

