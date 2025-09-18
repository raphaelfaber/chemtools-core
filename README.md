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

<pre><code>String testCase = "Ca(OH)2 + HCl -> CaCl2 + H2O";
Reaction reaction = ExtractEquation.extract(testCase);
reaction = BalanceEquation.balance(reaction);
System.out.println(reaction);
// Output: 1 Ca(OH)2 + 2 HCl -> 1 CaCl2 + 2 H2O
</code></pre>

<pre><code>String testCase = "KMnO4 + HCl -> KCl + MnCl2 + Cl2 + H2O";
Reaction reaction = ExtractEquation.extract(testCase);
reaction = BalanceEquation.balance(reaction);
System.out.println(reaction);
// Output: 2 KMnO4 + 16 HCl -> 2 KCl + 2 MnCl2 + 5 Cl2 + 8 H2O
</code></pre>

<h2>Test Structure (JUnit)</h2>
<ul>
    <li>Coverage includes:
        <ul>
            <li>Simple reactions: <code>H2 + O2 -> H2O</code></li>
            <li>Complex redox reactions: <code>KMnO4 + HCl</code></li>
            <li>Compounds with parentheses: <code>Ba(OH)2 + H3PO4 -> Ba3(PO4)2 + H2O</code></li>
            <li>Metal oxides and salts</li>
        </ul>
    </li>
</ul>

<pre><code>@Test
void extractTestAndBalance() throws InvalidReactionException {
    String expected = "1 Ca(OH)2 + 2 HCl -> 1 CaCl2 + 2 H2O";
    String testCase = "Ca(OH)2 + HCl -> CaCl2 + H2O";
    Reaction reaction = ExtractEquation.extract(testCase);
    reaction = BalanceEquation.balance(reaction);
    String result = reaction.toString();
    assert expected.equals(result) : "Expected:" + expected + ", actual:" + result;
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

