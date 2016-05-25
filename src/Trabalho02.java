import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;

public class Trabalho02 {
	
	public static void main(String args[]) {
		
		String nome = "D://trabalho02/teste.txt";
		
		try {
			System.setIn(new FileInputStream(new File(nome)));
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo não encontrado");
		}
		
		Scanner sc = new Scanner(System.in);
		sc.useLocale(Locale.ENGLISH);
		Locale.setDefault(new Locale("en", "US"));

		String A[],B[];
		int C[],N,i,pos,cont;
		double D[],E[],M[][],soma,media;
		
//quantidade de clientes  que será digitado	
		System.out.print("Quantos clientes irá inserir: ");
		N = sc.nextInt();
		
		A = new String[N];
		B = new String[N];
		C = new int[N];
		D = new double[N];
		E = new double[N];
		M = new double[3][2];
		
//inclusao dos clientes		
		for(i=0;i<N;i++) {
			System.out.println("\nInsira o cadastro do "+(i+1)+"º cliente: ");
			System.out.print("Nome: ");
			sc.nextLine();
			A[i] = sc.nextLine();
			System.out.print("Telefone: ");
			B[i] = sc.next();
			System.out.print("Tipo da conta (0, 1, 2): ");
			C[i] = sc.nextInt();
			System.out.print("Minutos usados:");
			D[i] = sc.nextDouble();
		}
		
//custo de assinatura e tarifa		
		for(i=0;i<3;i++) {
			System.out.print("\nInforme o valor da assinatura tipo "+i+": ");
			M[i][0] = sc.nextDouble();
			System.out.print("Informe a tarifa excedente da assinatura Tipo "+i+": ");
			M[i][1] = sc.nextDouble();
		}
//calculando do valor da fatura de cada cliente
		for(i=0;i<N;i++) {
			if (D[i] > 90 && C[i] == 0) {
				E[i] = (D[i] - 90) * M[0][1] + M[0][0];
			}
			else if (D[i] > 90 && C[i] == 1) {
				E[i] = (D[i] - 90) * M[1][1] + M[1][0];
			}
			else if (D[i] > 90 && C[i] == 2) {
				E[i] = (D[i] - 90) * M[2][1] + M[2][0];
			}
			else if (D[i] <= 90 && C[i] == 0) {
				E[i] = M[0][0];
			}
			else if (D[i] <= 90 && C[i] == 1) {
				E[i] = M[1][0];
			}
			else if (D[i] <= 90 && C[i] == 2) {
				E[i] = M[2][0];
			}
		}	
		
//1 - Imprimindo a tabela completa dos clientes e mostrando a Receita total
		System.out.println("\nCadastro dos clientes:");
		soma = 0; 
		System.out.println("\tNome\t | Telefone\t | Tipo\t | Minutos\t | Valor da conta");
		for(i=0;i<N;i++) {
			 System.out.println(A[i]+"\t | "+B[i]+"\t | "+C[i]+"\t | "+D[i]+" min.\t | R$ "+E[i]);
			 soma = soma + E[i];
		 }
		System.out.println("\nReceita total da companhia telefônica:");
		System.out.println("R$ "+soma);

//2 - calculo da conta mais barata
		 System.out.println("\nConta mais barata do cadastro:");
		 pos = 0;
		 for(i=1;i<N;i++) {
			if(E[i] < E[pos]) {
				pos = i;
			}
		 }
		 System.out.println("\tNome\t | Telefone");
		 System.out.println(A[pos]+"\t | "+B[pos]);
		 
//3 - Média de minutos consumidos por clientes de conta tipo 1		 
		 System.out.println("\nMédia dos minutos consumidos de clientes conta tipo 1:");
		 media = 0;
		 soma = 0;
		 cont = 0;
		 for(i=0;i<N;i++) {
			 if(C[i] == 1) {
				 soma = soma + D[i];
				 cont = cont + 1;
			 }
		 }
		 media = soma / cont;
		 System.out.printf("%.2f min.\n",media);
		 
//4 - Nomes e telefones dos clientes que não consumiram minutos excedentes
		 System.out.println("\nClientes sem minutos excedentes:");
		 System.out.println("\tNome\t | Telefone");
	     for(i=1;i<N;i++) {
	    	 if(D[i] <= 90) {
				 System.out.println(A[i]+"\t | "+B[i]);
			 }
	     }	 
//5 - A quantidade de clientes que consumiu acima de 120 minutos
	     cont = 0;
		 for(i=0;i<N;i++) {
			 if(D[i] > 120) {
				 cont = cont + 1;
			 }
		 }
		 System.out.println("\nQuantidade de clientes que consumiram acima de 120 min.");
		 System.out.println(cont);
		 
//6 - A porcentagem de clientes que possuem conta tipo 2, em relação ao total de clientes
		 media = 0;
		 cont = 0;
		 for(i=0;i<N;i++) {
			 if(C[i] == 2) {
				 cont = cont + 1;				 
			 }
		 }
		 media = (double) cont / N * 100;	 
         System.out.println("\nPorcentagem de clientes de conta tipo 2:");
	     System.out.printf("%.2f porcento",media);    
	     
	sc.close();
	}
}