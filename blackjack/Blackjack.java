package blackjack;

import javax.swing.*;

class Blackjack {

	public static void main(String[] args) {

		/* inicializacion de las variables */
		int opcion = -1, puntajecrupier, puntajenuestro;
		double saldo = 0, retiro, apuesta;
		boolean esturnodecrupier, quieremas, queremosmas, cartarepetida;
		int azar;
		String carta;

		/* Variables de las cartas */
		String[] palos = new String[] { "C", "T", "P", "D" };
		String[] simbolos = new String[] { "A", "2", "3", "4", "5", "6", "7", "8", "9", "J", "K", "Q" };
		int[] valores = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10 };
		String[] cartasenmesa = new String[15];
		int numerodecartas;
		int resultado; /*
						 * esta variable va a tener tres alternativas ganar>0; peder<0; empatar=0; no se
						 * sabe=-1
						 */

		JOptionPane.showMessageDialog(null,
				"Bienvenidos al Black Jack \n\nLas reglas son: \nEl objetivo del blackjack es derrotar al crupier. Para esto, debes tener una mano que puntúe más alto que la mano del crupier, \npero que no supere los 21 puntos en valor total \nEl crupier siempre se va a plantar con 17 o más puntos.\nEn este casino el As vale 1. \n\nComo se juga: \nPara abrir una apuesta primero tendras que ingresar un monto.",
				"Black jack", JOptionPane.DEFAULT_OPTION,
				new ImageIcon(Blackjack.class.getResource("/img1/reglas (1).jpg")));
		do {

			opcion = Integer.parseInt((String) JOptionPane.showInputDialog(null,
					"Menu de Opciones \n 1 Abrir apuesta \n 2 incrementar saldo \n 3 consultar saldo \n 4 retirar dinero \n 0 cerrar \ningrese la opcion: ",
					"Black jack", JOptionPane.DEFAULT_OPTION,
					new ImageIcon(Blackjack.class.getResource("/img1/inicio.jpg")), null, null));
			switch (opcion) {
			case 1: {
				/* se inicia una apuesta */
				if (saldo <= 0) {
					JOptionPane.showMessageDialog(null, "no se puede apostar sin saldo, porfavor pase por opcion 2");

				}
				apuesta = Double.parseDouble((String) JOptionPane.showInputDialog(null, "ingrese su apuesta: ",
						"Black jack", JOptionPane.DEFAULT_OPTION,
						new ImageIcon(Blackjack.class.getResource("/img1/ingresar.gif")), null, null));
				if (apuesta > saldo) {
					JOptionPane.showMessageDialog(null, "saldo insuficiente, porfavor pase por opcion 2");

				}
				if (apuesta == 0) {
					JOptionPane.showMessageDialog(null, "no se puede apostar sin saldo, porfavor pase por opcion 2");

				}

				JOptionPane.showMessageDialog(null, "incio del juego \nEl Crupier reparte las cartas", "Repartiendo",
						JOptionPane.DEFAULT_OPTION,
						new ImageIcon(Blackjack.class.getResource("/img1/repartiendo.jpg")));
				cartasenmesa = new String[15];
				numerodecartas = 0;
				esturnodecrupier = true;
				puntajecrupier = 0;
				puntajenuestro = 0;
				quieremas = true;
				queremosmas = true;
				resultado = -1; /* se establece que todavia no se sabe el resultado de la partida */
				while (quieremas || queremosmas) {
					do {

						carta = "";

						/* generacion de la carta */
						azar = (int) (Math.random() * 4);
						carta = palos[azar];
						azar = (int) (Math.random() * 12);
						carta = simbolos[azar] + carta;
						/* validamos que la carta no se repita */
						cartarepetida = false;
						for (int i = 0; i < numerodecartas; i++) {
							if (carta == cartasenmesa[i]) {
								cartarepetida = true;
							}
						}
					} while (cartarepetida);
					/* se guarda la carta como carta en la mesa */
					cartasenmesa[numerodecartas] = carta;
					numerodecartas++;
					if (esturnodecrupier) {
						if (quieremas) {
							puntajecrupier += valores[azar];
							JOptionPane.showMessageDialog(null,
									"valor de la carta del crupier: " + valores[azar] + "\npuntaje: " + puntajecrupier,
									"Blacj jack (turno crupier)", JOptionPane.DEFAULT_OPTION,
									new ImageIcon(Blackjack.class.getResource("/img/" + carta + ".gif")));

							if (puntajecrupier >= 17) {
								quieremas = false;

							}

							if (puntajecrupier > 21) {
								resultado = 2;

							}

							if (!queremosmas) {
								JOptionPane.showMessageDialog(null,
										"valor de las cartas del crupier: " + puntajecrupier
												+ "\n valor de nuestras cartas: " + puntajenuestro,
										"Black jack", JOptionPane.DEFAULT_OPTION,
										new ImageIcon(Blackjack.class.getResource("/img1/puntos (1).jpg")));
							}

						}

					} else if (queremosmas) {
						puntajenuestro += valores[azar];
						JOptionPane.showMessageDialog(null,
								"valor de nuestra carta: " + valores[azar] + "\npuntaje: " + puntajenuestro,
								"Black jack (nuestro turno)", JOptionPane.DEFAULT_OPTION,
								new ImageIcon(Blackjack.class.getResource("/img/" + carta + ".gif")));
						if (puntajenuestro <= 21) {

							if (numerodecartas >= 4) {
								JOptionPane.showMessageDialog(null,
										"valor de las cartas del crupier: " + puntajecrupier
												+ "\n valor de nuestras cartas: " + puntajenuestro,
										"Black jack", JOptionPane.DEFAULT_OPTION,
										new ImageIcon(Blackjack.class.getResource("/img1/puntos (1).jpg")));
								int pedir = Integer.parseInt((String) JOptionPane.showInputDialog(null,
										"pedir carta \n1=Si quiero \n0=No quiero", "pedir", JOptionPane.DEFAULT_OPTION,
										new ImageIcon(Blackjack.class.getResource("/img1/pedir (2).jpg")), null, null));
								if (pedir == 0) {
									queremosmas = false;
								}

							}

						} else {
							queremosmas = false;
							resultado = -2;

						}

					}

					esturnodecrupier = !esturnodecrupier;

				}
				if (resultado == -1) {
					resultado = puntajenuestro - puntajecrupier;
				}
				if (resultado > 0) {
					/* hemos ganado el partido */
					JOptionPane.showMessageDialog(null, "felicidades has ganado: " + apuesta, "ganador",
							JOptionPane.DEFAULT_OPTION,
							new ImageIcon(Blackjack.class.getResource("/img1/ganador (1).jpg")));
					saldo = saldo + apuesta;
				} else if (resultado == 0) {
					JOptionPane.showMessageDialog(null, "has empatado ", "empate", JOptionPane.DEFAULT_OPTION,
							new ImageIcon(Blackjack.class.getResource("/img1/ganador (1).jpg")));
				} else {
					JOptionPane.showMessageDialog(null, "has perdido: " + apuesta, "pederdio",
							JOptionPane.DEFAULT_OPTION,
							new ImageIcon(Blackjack.class.getResource("/img1/perdio (1).jpg")));
					saldo = saldo - apuesta;
				}
				JOptionPane.showMessageDialog(null, "Tu saldo es de: " + saldo);

				break;
			}
			case 2: {
				/* se incrementa el saldo */
				saldo += Double.parseDouble((String) JOptionPane.showInputDialog(null, "ingrese saldo: ", "ingresar",
						JOptionPane.DEFAULT_OPTION, new ImageIcon(Blackjack.class.getResource("/img1/ingresar.gif")),
						null, null));
				cartasenmesa = new String[15];
				break;
			}
			case 3: {
				/* consultar saldo */
				JOptionPane.showMessageDialog(null, "su saldo es: " + saldo);
				break;
			}
			case 4: {
				/* retirar dinero */
				retiro = Double.parseDouble((String) JOptionPane.showInputDialog(null, "cuando quiere retirar: ",
						"retirar", JOptionPane.DEFAULT_OPTION,
						new ImageIcon(Blackjack.class.getResource("/img1/retirar.gif")), null, null));
				if (retiro <= saldo) {
					saldo -= retiro;
				} else {
					JOptionPane.showMessageDialog(null, "saldo insuficiente");
				}
				break;
			}
			case 0: {
				JOptionPane.showMessageDialog(null, "gracias por jugar");
				break;
			}
			default:
				JOptionPane.showMessageDialog(null, "opcion inexistente");
			}
		} while (opcion != 0);

	}

}
