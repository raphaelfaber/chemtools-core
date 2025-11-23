<h1>ChemTools – Stoichiometry and Chemical Equation Engine</h1>

<p><strong>ChemTools</strong> is a Java library that parses, balances, and performs stoichiometric calculations on chemical equations. It provides a fully object-oriented model where atoms, molecules, reactions, and proportional scaling are treated as first-class elements.</p>

<h2>Key Features</h2>
<ul>
    <li>Parse chemical equations directly from plain text.</li>
    <li>Automatically balance reactions using algebraic matrix solving.</li>
    <li>Object representation of atoms, molecules, reaction coefficients, and full equations.</li>
    <li>Stoichiometric calculations:
        <ul>
            <li>Mass calculation</li>
            <li>Mole calculation</li>
            <li>Proportional adjustment based on a selected reference molecule</li>
        </ul>
    </li>
    <li>Optional rounding to any number of decimal places.</li>
    <li>Well-defined exceptions for:
        <ul>
            <li>Invalid formulas</li>
            <li>Unknown molecules</li>
            <li>Malformed equations</li>
            <li>Unbalanced reactions</li>
        </ul>
    </li>
</ul>

<h2>How It Works</h2>
<p>The library receives an equation as a string (e.g. <code>"H2 + O2 => H2O"</code>), parses it into a reaction object, builds a matrix with atomic counts, solves it mathematically, and assigns the calculated stoichiometric coefficients to each molecule.</p>

<h2>Basic Example (from tests)</h2>

<p>Given the reaction:</p>

<pre><code>H2 + O2 => H2O</code></pre>

<p>You can parse and balance it like this:</p>

<pre><code>Equation eq = Equation.factory("H2 + O2 => H2O");
eq.balance();
</code></pre>

<p>After balancing, the equation becomes:</p>

<pre><code>2H2 + O2 => 2H2O</code></pre>

<h2>Stoichiometric Calculation</h2>

<p>The calculator allows specifying a reference molecule and how many moles of it are used. It will then compute all derived values for the entire reaction.</p>

<pre><code>StoichiometricCalculator calc = new StoichiometricCalculator(eq);
calc.setReferenceMolecule("H2");
calc.setRefenceMoles(4, 1);
</code></pre>

<p>Now the reaction becomes:</p>

<pre><code>4H2 + 2O2 => 4H2O</code></pre>

<h3>Mass Calculation</h3>

<p>This is tested in the unit tests:</p>

<pre><code>assertEquals(8,  calc.calculateMass("H2"),  DELTA);
assertEquals(64, calc.calculateMass("O2"),  DELTA);
assertEquals(72, calc.calculateMass("H2O"), DELTA);
</code></pre>

<p>Meaning:</p>
<ul>
    <li>4 moles of H₂ = 8 g</li>
    <li>2 moles of O₂ = 64 g</li>
    <li>4 moles of H₂O = 72 g</li>
</ul>

<h3>Mole Calculation</h3>

<pre><code>assertEquals(4, calc.calculateMoles("H2"),  DELTA);
assertEquals(2, calc.calculateMoles("O2"),  DELTA);
assertEquals(4, calc.calculateMoles("H2O"), DELTA);
</code></pre>

<p>The return value is simply the stoichiometric coefficient after scaling.</p>

<h3>Rounding</h3>

<p>Results can be rounded:</p>

<pre><code>calc.calculateMass("H2", 2)  // -> 8.00
calc.calculateMoles("H2", 3) // -> 4.000
</code></pre>

<h2>Error Handling Examples</h2>

<p>If a molecule does not exist in the reaction:</p>

<pre><code>assertThrows(
    MoleculeNotFoundException.class,
    () -> calc.calculateMass("Xx")
);
</code></pre>

<p>If a formula is syntactically invalid:</p>

<pre><code>assertThrows(
    InvalidMoleculeException.class,
    () -> calc.calculateMoles("123")
);
</code></pre>

<h2>Folder Structure</h2>

<p>Main components are located under:</p>

<ul>
    <li><strong>entities</strong> – Atom, Molecule, MoleculeInReaction, Equation</li>
    <li><strong>parser</strong> – Responsible for interpreting reaction text</li>
    <li><strong>business</strong> – Includes StoichiometricCalculator</li>
    <li><strong>externaltools</strong> – Matrix calculation system</li>
    <li><strong>comparators</strong> – Utility comparators</li>
</ul>

<h2>Automated Tests</h2>

<p>A complete JUnit 5 suite can be found in <code>src/test/java</code>. It includes:</p>

<ul>
    <li>Equation parsing and balance validation</li>
    <li>Mass and mole calculations</li>
    <li>Rounding rules</li>
    <li>Input validation and error handling</li>
</ul>

<h2>Requirements</h2>
<ul>
    <li>Java 17+</li>
    <li>JUnit 5 (for tests)</li>
</ul>

<h2>License</h2>
<p>Distributed freely with no warranty of any kind.</p>
