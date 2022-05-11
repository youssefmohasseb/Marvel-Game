package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.Condition;

/////
import model.world.*;

////
import org.junit.Test;

@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
public class Quiz1V1 {
	String abilityPath = "model.abilities.Ability";
	String areaOfEffectPath = "model.abilities.AreaOfEffect";
	String crowdControlAbilityPath = "model.abilities.CrowdControlAbility";
	String damagingAbilityPath = "model.abilities.DamagingAbility";
	String healingAbilityPath = "model.abilities.HealingAbility";
	String disarmPath = "model.effects.Disarm";
	String effectPath = "model.effects.Effect";
	String effectTypePath = "model.effects.EffectType";

	/////
	String typePath = "model.world.NourishmentType";
	String lootedItemPath = "model.world.LootedItem";
	String boughtItemPath = "model.world.BoughtItem";
	String nourishmentPath = "model.world.Nourishment";
	/////

	String embracePath = "model.effects.Embrace";
	String powerUpPath = "model.effects.PowerUp";
	String shieldPath = "model.effects.Shield";
	String rootPath = "model.effects.Root";
	String shockPath = "model.effects.Shock";
	String silencePath = "model.effects.Silence";
	String DodgePath = "model.effects.Dodge";
	String speedUpPath = "model.effects.SpeedUp";
	String stunPath = "model.effects.Stun";

	String directionPath = "model.world.Direction";
	String conditionPath = "model.world.Condition";
	String coverPath = "model.world.Cover";
	String championPath = "model.world.Champion";
	String antiHeroPath = "model.world.AntiHero";
	String heroPath = "model.world.Hero";
	String villainPath = "model.world.Villain";
	String abilityExceptionPath = "exceptions.AbilityUseException";
	String gameExceptionPath = "exceptions.GameActionException";
	String notYourTurnExceptionPath = "exceptions.NotYourTurnException";
	String leaderAbilityAlreadyUsedExceptionPath = "exceptions.LeaderAbilityAlreadyUsedException";
	String unallowedMovementExceptionPath = "exceptions.UnallowedMovementException";

	String gamePath = "engine.Game";
	String playerPath = "engine.Player";
	String priorityQueuePath = "engine.PriorityQueue";

	String healingPath = "model.abilities.HealingAbility";
	String dmgPath = "model.abilities.DamagingAbility";
	String abilitiesPath = "model.abilities.Ability";
	String ccAbilitiesPath = "model.abilities.CrowdControlAbility";

	String champPath = "model.world.Champion";

	///// Enum tests
	@Test(timeout = 1000)
	public void testClassType() throws Exception {
		testIsEnum(Class.forName(typePath));
	}

	@Test(timeout = 1000)
	public void testEnumValuesType() {
		String[] inputs = { "FOOD", "DRINK" };
		testEnumValues("NourishmentType", typePath, inputs);
	}

	///// inheritance tests

	// super/subclass
	@Test(timeout = 1000)
	public void testNourishmentisSuperClassOfLootedItem() throws Exception {
		testClassIsSubclass(Class.forName(lootedItemPath), Class.forName(nourishmentPath));
	}

	@Test(timeout = 1000)
	public void testNourishmentisSuperClassOfBoughtItem() throws Exception {
		testClassIsSubclass(Class.forName(boughtItemPath), Class.forName(nourishmentPath));
	}
	// constructors tests

	@Test(timeout = 1000)
	public void testConstructorOfNourishment() throws Exception {
		Class[] inputs = { String.class, int.class, int.class, Class.forName(typePath) };
		testConstructorExists(Class.forName(nourishmentPath), inputs);
	}

	@Test(timeout = 1000)
	public void testConstructorOfLootedItem() throws Exception {
		Class[] inputs = { String.class, int.class, int.class, Class.forName(typePath), int.class };
		testConstructorExists(Class.forName(lootedItemPath), inputs);
	}

	@Test(timeout = 1000)
	public void testConstructorOfBouhgtItem() throws Exception {
		Class[] inputs = { String.class, int.class, int.class, Class.forName(typePath), int.class };
		testConstructorExists(Class.forName(boughtItemPath), inputs);
	}

	@Test(timeout = 100)
	public void testConstructorChampionInitializationQuiz() throws Exception {
		Constructor<?> constructor = Class.forName(championPath).getConstructor(String.class, int.class, int.class,
				int.class, int.class, int.class, int.class);
		int randomMaxHP = (int) (Math.random() * 10) + 1;
		int randomMana = (int) (Math.random() * 10) + 1;
		int randomActions = (int) (Math.random() * 10) + 1;
		int randomSpeed = (int) (Math.random() * 10) + 1;
		int randomAttackRange = (int) (Math.random() * 10) + 1;
		int randomAttackDamage = (int) (Math.random() * 10) + 1;
		int randomName = (int) (Math.random() * 10) + 1;
		Object b = constructor.newInstance("Name_" + randomName, randomMaxHP, randomMana, randomActions, randomSpeed,
				randomAttackRange, randomAttackDamage);
		String[] varNames = { "name", "maxHP", "currentHP", "mana", "maxActionPointsPerTurn", "currentActionPoints",
				"attackRange", "attackDamage", "speed", "myCoins", "abilities", "appliedEffects", "condition",
				"location", "consumables" };
		Object[] varValues = { "Name_" + randomName, randomMaxHP, randomMaxHP, randomMana, randomActions, randomActions,
				randomAttackRange, randomAttackDamage, randomSpeed, 1000, new ArrayList<Object>(),
				new ArrayList<Object>(), Enum.valueOf((Class<Enum>) Class.forName(conditionPath), "ACTIVE"), null,
				new ArrayList<Object>() };
		testConstructorInitialization(b, varNames, varValues);
	}

	@Test(timeout = 100)
	public void testConstructorNourishmentInitialization() throws Exception {
		Constructor<?> constructor = Class.forName(nourishmentPath).getConstructor(String.class, int.class, int.class,
				Class.forName(typePath));
		int randomManaIncreased = (int) (Math.random() * 10) + 1;
		int randomNourishmentAmount = (int) (Math.random() * 10) + 1;
		int randomName = (int) (Math.random() * 10) + 1;
		Object type00 = Enum.valueOf((Class<Enum>) Class.forName(typePath), "DRINK");

		Object b = constructor.newInstance("Name_" + randomName, randomNourishmentAmount, randomManaIncreased, type00);
		String[] varNames = { "name", "nourishmentAmount", "manaIncreased", "type" };
		Object[] varValues = { "Name_" + randomName, randomNourishmentAmount, randomManaIncreased,
				Enum.valueOf((Class<Enum>) Class.forName(typePath), "DRINK") };
		testConstructorInitialization(b, varNames, varValues);
	}
	@Test(timeout = 100)
	public void testConstructorNourishmentInitialization2() throws Exception {
		Constructor<?> constructor = Class.forName(nourishmentPath).getConstructor(String.class, int.class, int.class,
				Class.forName(typePath));
		int randomManaIncreased = (int) (Math.random() * 10) + 1;
		int randomNourishmentAmount = (int) (Math.random() * 10) + 1;
		int randomActionPointsNeeded = (int) (Math.random() * 10) + 1;
		int randomName = (int) (Math.random() * 10) + 1;
		Object type00 = Enum.valueOf((Class<Enum>) Class.forName(typePath), "DRINK");

		Object b = constructor.newInstance("Name_" + randomName, randomNourishmentAmount, randomManaIncreased, type00);
		String[] varNames = { "name" };
		Object[] varValues = { "Name_" + randomName };
		testConstructorInitialization(b, varNames, varValues);
	}

	@Test(timeout = 100)
	public void testConstructorNourishmentInitialization3() throws Exception {
		Constructor<?> constructor = Class.forName(nourishmentPath).getConstructor(String.class, int.class, int.class,
				Class.forName(typePath));
		int randomManaIncreased = (int) (Math.random() * 10) + 1;
		int randomNourishmentAmount = (int) (Math.random() * 10) + 1;
		int randomActionPointsNeeded = (int) (Math.random() * 10) + 1;
		int randomName = (int) (Math.random() * 10) + 1;
		Object type00 = Enum.valueOf((Class<Enum>) Class.forName(typePath), "DRINK");

		Object b = constructor.newInstance("Name_" + randomName, randomNourishmentAmount, randomManaIncreased, type00);
		String[] varNames = { "nourishmentAmount" };
		Object[] varValues = { randomNourishmentAmount };
		testConstructorInitialization(b, varNames, varValues);
	}

	@Test(timeout = 100)
	public void testConstructorNourishmentInitialization4() throws Exception {
		Constructor<?> constructor = Class.forName(nourishmentPath).getConstructor(String.class, int.class, int.class,
				Class.forName(typePath));
		int randomManaIncreased = (int) (Math.random() * 10) + 1;
		int randomNourishmentAmount = (int) (Math.random() * 10) + 1;
		int randomActionPointsNeeded = (int) (Math.random() * 10) + 1;
		int randomName = (int) (Math.random() * 10) + 1;
		Object type00 = Enum.valueOf((Class<Enum>) Class.forName(typePath), "DRINK");

		Object b = constructor.newInstance("Name_" + randomName, randomNourishmentAmount, randomManaIncreased, type00);
		String[] varNames = { "manaIncreased" };
		Object[] varValues = { randomManaIncreased };
		testConstructorInitialization(b, varNames, varValues);
	}

	@Test(timeout = 100)
	public void testConstructorNourishmentInitialization5() throws Exception {
		Constructor<?> constructor = Class.forName(nourishmentPath).getConstructor(String.class, int.class, int.class,
				Class.forName(typePath));
		int randomManaIncreased = (int) (Math.random() * 10) + 1;
		int randomNourishmentAmount = (int) (Math.random() * 10) + 1;
		int randomActionPointsNeeded = (int) (Math.random() * 10) + 1;
		int randomName = (int) (Math.random() * 10) + 1;
		Object type00 = Enum.valueOf((Class<Enum>) Class.forName(typePath), "DRINK");

		Object b = constructor.newInstance("Name_" + randomName, randomNourishmentAmount, randomManaIncreased, type00);
		String[] varNames = { "type" };
		Object[] varValues = { Enum.valueOf((Class<Enum>) Class.forName(typePath), "DRINK") };
		testConstructorInitialization(b, varNames, varValues);
	}


	@Test(timeout = 100)
	public void testConstructorLootedItemInitialization() throws Exception {
		Constructor<?> constructor = Class.forName(lootedItemPath).getConstructor(String.class, int.class, int.class,
				Class.forName(typePath), int.class);
		int randomManaIncreased = (int) (Math.random() * 10) + 1;
		int randomNourishmentAmount = (int) (Math.random() * 10) + 1;
		int randomActionPointsNeeded = (int) (Math.random() * 10) + 1;
		int randomName = (int) (Math.random() * 10) + 1;
		Object type00 = Enum.valueOf((Class<Enum>) Class.forName(typePath), "DRINK");

		Object b = constructor.newInstance("Name_" + randomName, randomNourishmentAmount, randomManaIncreased, type00,
				randomActionPointsNeeded);
		String[] varNames = { "name", "nourishmentAmount", "manaIncreased", "type" };
		Object[] varValues = { "Name_" + randomName, randomNourishmentAmount, randomManaIncreased,
				Enum.valueOf((Class<Enum>) Class.forName(typePath), "DRINK")};
		testConstructorInitialization(b, varNames, varValues);
	}

	
	@Test(timeout = 100)
	public void testConstructorLootedItemInitialization2() throws Exception {
		Constructor<?> constructor = Class.forName(lootedItemPath).getConstructor(String.class, int.class, int.class,
				Class.forName(typePath), int.class);
		int randomManaIncreased = (int) (Math.random() * 10) + 1;
		int randomNourishmentAmount = (int) (Math.random() * 10) + 1;
		int randomActionPointsNeeded = (int) (Math.random() * 10) + 1;
		int randomName = (int) (Math.random() * 10) + 1;
		Object type00 = Enum.valueOf((Class<Enum>) Class.forName(typePath), "DRINK");

		Object b = constructor.newInstance("Name_" + randomName, randomNourishmentAmount, randomManaIncreased, type00,
				randomActionPointsNeeded);
		String[] varNames = { "actionPointsNeeded" };
		Object[] varValues = { randomActionPointsNeeded };
		testConstructorInitialization(b, varNames, varValues);
	}

	@Test(timeout = 100)
	public void testConstructorBoughtItemInitialization() throws Exception {
		Constructor<?> constructor = Class.forName(boughtItemPath).getConstructor(String.class, int.class, int.class,
				Class.forName(typePath), int.class);
		int randomManaIncreased = (int) (Math.random() * 10) + 1;
		int randomNourishmentAmount = (int) (Math.random() * 10) + 1;
		int randomPrice = (int) (Math.random() * 10) + 1;
		int randomName = (int) (Math.random() * 10) + 1;
		Object type00 = Enum.valueOf((Class<Enum>) Class.forName(typePath), "DRINK");

		Object b = constructor.newInstance("Name_" + randomName, randomNourishmentAmount, randomManaIncreased, type00,
				randomPrice);
		String[] varNames = { "name", "nourishmentAmount", "manaIncreased", "type" };
		Object[] varValues = { "Name_" + randomName, randomNourishmentAmount, randomManaIncreased,
				Enum.valueOf((Class<Enum>) Class.forName(typePath), "DRINK") };
		testConstructorInitialization(b, varNames, varValues);
	}

	@Test(timeout = 100)
	public void testConstructorBoughtItemInitialization2() throws Exception {
		Constructor<?> constructor = Class.forName(boughtItemPath).getConstructor(String.class, int.class, int.class,
				Class.forName(typePath), int.class);
		int randomManaIncreased = (int) (Math.random() * 10) + 1;
		int randomNourishmentAmount = (int) (Math.random() * 10) + 1;
		int randomPrice = (int) (Math.random() * 10) + 1;
		int randomName = (int) (Math.random() * 10) + 1;
		Object type00 = Enum.valueOf((Class<Enum>) Class.forName(typePath), "DRINK");

		Object b = constructor.newInstance("Name_" + randomName, randomNourishmentAmount, randomManaIncreased, type00,
				randomPrice);
		String[] varNames = { "price" };
		Object[] varValues = { randomPrice };
		testConstructorInitialization(b, varNames, varValues);
	}

	@Test(timeout = 1000)
	public void testSetterForInstanceVariableNourishmentName() throws Exception {
		testSetterMethodExistsInClass(Class.forName(nourishmentPath), "setName", String.class, true);
	}

	@Test(timeout = 1000)
	public void testSetterForInstanceVariableNourishmentAmount() throws Exception {
		testSetterMethodExistsInClass(Class.forName(nourishmentPath), "setNourishmentAmount", int.class, true);
	}

	@Test(timeout = 1000)
	public void testSetterForInstanceVariableManaIncreased() throws Exception {
		testSetterMethodExistsInClass(Class.forName(nourishmentPath), "setManaIncreased", int.class, true);
	}

	@Test(timeout = 1000)
	public void testSetterForInstanceVariableType() throws Exception {
		testSetterMethodExistsInClass(Class.forName(nourishmentPath), "setType", NourishmentType.class, false);
	}

	// setter of BoughtItem

	@Test(timeout = 1000)
	public void testSetterForInstanceVariablePrice() throws Exception {
		testSetterMethodExistsInClass(Class.forName(boughtItemPath), "setPrice", int.class, true);
	}

	// setter of LootedItem

	@Test(timeout = 1000)
	public void testSetterForInstanceVariableActionPointsNeeded() throws Exception {
		testSetterMethodExistsInClass(Class.forName(lootedItemPath), "setActionPointsNeeded", int.class, true);
	}

	// setters of Champion class

	@Test(timeout = 1000)
	public void testSetterForInstanceVariableConsumables() throws Exception {
		testSetterMethodExistsInClass(Class.forName(championPath), "setConsumables", ArrayList.class, true);
	}

	@Test(timeout = 1000)
	public void testSetterForInstanceVariableMyCoins() throws Exception {
		testSetterMethodExistsInClass(Class.forName(championPath), "setMyCoins", int.class, true);
	}

	// isPrivate tests
	@Test(timeout = 1000)
	public void testInstanceVariableTypeIsPrivateInClassNourishment() throws Exception {
		testInstanceVariableIsPrivate(Class.forName(nourishmentPath), "type");
	}

	@Test(timeout = 1000)
	public void testInstanceVariableNameIsPrivateInClassNourishment() throws Exception {
		testInstanceVariableIsPrivate(Class.forName(nourishmentPath), "name");
	}

	@Test(timeout = 1000)
	public void testInstanceVariableNourishementAmountIsPrivateInClassNourishment() throws Exception {
		testInstanceVariableIsPrivate(Class.forName(nourishmentPath), "nourishmentAmount");
	}

	@Test(timeout = 1000)
	public void testInstanceVariableManaIncreasedIsPrivateInClassNourishment() throws Exception {
		testInstanceVariableIsPrivate(Class.forName(nourishmentPath), "manaIncreased");
	}

	// getter does not exist

	@Test(timeout = 100)
	public void testInstanceVariableNourishemntManaIncreasedGetter() throws ClassNotFoundException {
		testGetterMethodExistsInClass(Class.forName(nourishmentPath), "getManaIncreased", int.class, false);
	}

	// variables types tests

	@Test(timeout = 100)
	public void testConsumablesInstanceVariableChampion() throws Exception {
		testInstanceVariableIsPresent(Class.forName(championPath), "consumables", true);
		testInstanceVariableIsPrivate(Class.forName(championPath), "consumables");
		testInstanceVariableOfType(Class.forName(championPath), "consumables", ArrayList.class);

	}

	@Test(timeout = 100)
	public void testCoinsInstanceVariableChampion() throws Exception {
		testInstanceVariableOfType(Class.forName(championPath), "myCoins", int.class);

	}

	@Test(timeout = 100)
	public void testNameInstanceVariableLootedItem() throws Exception {
		testInstanceVariableOfType(Class.forName(nourishmentPath), "name", String.class);

	}

	@Test(timeout = 100)
	public void testNourishmentAmountInstanceVariableNourishment() throws Exception {
		testInstanceVariableOfType(Class.forName(nourishmentPath), "nourishmentAmount", int.class);

	}

	@Test(timeout = 100)
	public void testManaIncreasedInstanceVariableNourishment() throws Exception {
		testInstanceVariableOfType(Class.forName(nourishmentPath), "manaIncreased", int.class);

	}

	@Test(timeout = 100)
	public void testTypeInstanceVariableNourishment() throws Exception {
		testInstanceVariableOfType(Class.forName(nourishmentPath), "type", Class.forName(typePath));

	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private void testIsEnum(Class aClass) {

		assertEquals(aClass.getName() + " should be an Enum", true, aClass.isEnum());

	}

	private static void testEnumValues(String name, String path, String[] value) {

		try {
			Class aClass = Class.forName(path);
			for (int i = 0; i < value.length; i++) {
				try {
					Enum.valueOf((Class<Enum>) aClass, value[i]);
				} catch (IllegalArgumentException e) {
					fail(aClass.getSimpleName() + " enum can be " + value[i]);
				}
			}
		} catch (ClassNotFoundException e1) {

			fail("There should be an enum called " + name + "in package " + path);
		}

	}

	private void testConstructorExists(Class aClass, Class[] inputs) {
		boolean thrown = false;
		try {
			aClass.getConstructor(inputs);
		} catch (NoSuchMethodException e) {
			thrown = true;
		}

		if (inputs.length > 0) {
			String msg = "";
			int i = 0;
			do {
				msg += inputs[i].getSimpleName() + " and ";
				i++;
			} while (i < inputs.length);

			msg = msg.substring(0, msg.length() - 4);

			assertFalse(
					"Missing constructor with " + msg + " parameter" + (inputs.length > 1 ? "s" : "") + " in "
							+ aClass.getSimpleName() + " class.",

					thrown);
		} else
			assertFalse("Missing constructor with zero parameters in " + aClass.getSimpleName() + " class.",

					thrown);

	}

	private void testConstructorInitialization(Object createdObject, String[] names, Object[] values)
			throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException {

		for (int i = 0; i < names.length; i++) {

			Field f = null;
			Class curr = createdObject.getClass();
			String currName = names[i];
			Object currValue = values[i];

			while (f == null) {

				if (curr == Object.class)
					fail("Class " + createdObject.getClass().getSimpleName() + " should have the instance variable \""
							+ currName + "\".");
				try {
					f = curr.getDeclaredField(currName);
				} catch (NoSuchFieldException e) {
					curr = curr.getSuperclass();
				}

			}

			f.setAccessible(true);
			if (currName.equals("currentHP") || currName.equals("currentActionPoints")) {

				assertEquals(
						"The constructor of the " + createdObject.getClass().getSimpleName()
								+ " class should initialize the instance variable \"" + currName
								+ "\" correctly. It should be equals to the max initially.",
						currValue, f.get(createdObject));
			} else {
				assertEquals(
						"The constructor of the " + createdObject.getClass().getSimpleName()
								+ " class should initialize the instance variable \"" + currName + "\" correctly.",
						currValue, f.get(createdObject));
			}
		}

	}

	private void testSetterMethodExistsInClass(Class aClass, String methodName, Class inputType,
			boolean writeVariable) {

		Method[] methods = aClass.getDeclaredMethods();
		String varName = methodName.substring(3, 4).toLowerCase() + methodName.substring(4);
		if (writeVariable) {
			assertTrue("The \"" + varName + "\" instance variable in class " + aClass.getSimpleName()
					+ " is a WRITE variable.", containsMethodName(methods, methodName));
		} else {
			assertFalse("The \"" + varName + "\" instance variable in class " + aClass.getSimpleName()
					+ " is not a WRITE variable.", containsMethodName(methods, methodName));
			return;
		}
		Method m = null;
		boolean found = true;
		try {
			m = aClass.getDeclaredMethod(methodName, inputType);
		} catch (NoSuchMethodException e) {
			found = false;
		}

		assertTrue(aClass.getSimpleName() + " class should have " + methodName + " method that takes one "
				+ inputType.getSimpleName() + " parameter.", found);

		assertTrue("Incorrect return type for " + methodName + " method in " + aClass.getSimpleName() + ".",
				m.getReturnType().equals(Void.TYPE));

	}

	private static boolean containsMethodName(Method[] methods, String name) {
		for (Method method : methods) {
			if (method.getName().equals(name))
				return true;
		}
		return false;
	}

	private void testInstanceVariableIsPrivate(Class aClass, String varName)
			throws NoSuchFieldException, SecurityException {
		Field f = aClass.getDeclaredField(varName);
		boolean b = 2 == f.getModifiers();
		assertTrue("The \"" + varName + "\" instance variable in class " + aClass.getSimpleName()
				+ " should not be accessed outside that class.", b);
	}

	private void testClassIsSubclass(Class subClass, Class superClass) {
		assertEquals(subClass.getSimpleName() + " class should be a subclass from " + superClass.getSimpleName() + ".",
				superClass, subClass.getSuperclass());
	}

	private void testInstanceVariableOfType(Class aClass, String varName, Class expectedType) {
		Field f = null;
		boolean b = true;
		try {
			f = aClass.getDeclaredField(varName);
		} catch (NoSuchFieldException e) {
			b = false;
		}
		if (b) {
			Class varType = f.getType();
			assertEquals("The attribute " + varType.getSimpleName() + " of instance variable: " + varName
					+ " should be of the type " + expectedType.getSimpleName(), expectedType, varType);
		} else {
			assertTrue("The instance variable \"" + varName + "\" should be declared in class " + aClass.getSimpleName()
					+ ".", false);
		}

	}

	private void testInstanceVariableIsPresent(Class aClass, String varName, boolean implementedVar)
			throws SecurityException {
		boolean thrown = false;
		try {
			aClass.getDeclaredField(varName);
		} catch (NoSuchFieldException e) {
			thrown = true;
		}
		if (implementedVar) {
			assertFalse(
					"There should be \"" + varName + "\" instance variable in class " + aClass.getSimpleName() + ".",
					thrown);
		} else {
			assertTrue("The instance variable \"" + varName + "\" should not be declared in class "
					+ aClass.getSimpleName() + ".", thrown);
		}
	}

	private void testGetterMethodExistsInClass(Class aClass, String methodName, Class returnedType,
			boolean readvariable) {
		Method m = null;
		boolean found = true;
		try {
			m = aClass.getDeclaredMethod(methodName);
		} catch (NoSuchMethodException e) {
			found = false;
		}

		String varName = "";
		if (returnedType == boolean.class)
			varName = methodName.substring(2, 3).toLowerCase() + methodName.substring(3);
		else
			varName = methodName.substring(3, 4).toLowerCase() + methodName.substring(4);
		if (readvariable) {
			assertTrue("The \"" + varName + "\" instance variable in class " + aClass.getSimpleName()
					+ " is a READ variable.", found);
			assertTrue("Incorrect return type for " + methodName + " method in " + aClass.getSimpleName() + " class.",
					m.getReturnType().isAssignableFrom(returnedType));
		} else {
			assertFalse("The \"" + varName + "\" instance variable in class " + aClass.getSimpleName()
					+ " is not a READ variable.", found);
		}

	}

	/////
}
