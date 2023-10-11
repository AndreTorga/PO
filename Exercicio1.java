public class Exercicio1 {
    public static void main(String[] args) {
        String[] URLs = {
                "https://www.dei.uc.pt/poao/exames",
                "http://www.scs.org/index.html",
                "https://www.nato.int/events",
                "https://www.btu.de/",
                "https://www.dei.uc.pt/poao/exames",
                "http://www.eth.ch/index.html",
                "http://www.osu.edu/",
        };
        String[][] paises = {
                {"pt", "Portugal"},
                {"org", "EUA"},
                {"fr", "França"},
                {"uk", "Reino Unido"},
                {"de", "Alemanha"},
                {"edu", "EUA"},
        };

        int[] matrizContador = new int[paises.length];
        String[][] retiraBarras = new String[URLs.length][];
        String[][] retiraPontos = new String[URLs.length][];

        if(verificaURLs(URLs)) {
            retiraPontosEBarrasURLs(URLs, retiraBarras, retiraPontos);
            int numeroCorrespondencias = matrizCorrespondencias(paises, retiraPontos, matrizContador);
            verificaPaisesComDiferentesPrefixos(paises, matrizContador);
            printCorrespondenciasEOutros(URLs, paises, matrizContador, numeroCorrespondencias);
        }
    }

    private static boolean verificaURLs(String[] URLs) {
        //Funcao que verifica se os URLs sao validos
        boolean valido = true;
        int contador1 = 0;
        for (String url : URLs) {
            if (url.startsWith("https://") || url.startsWith("http://")) {
                contador1++;
            }
            else{
                System.out.println(url + "  :   Este url não tem sintaxe correta(https://www / http://www)");
            }
        }
        if (contador1 != URLs.length){
            valido = false;
        }
        return valido;
    }

    private static void printCorrespondenciasEOutros(String[] URLs, String[][] paises, int[] matrizCorrespondencias, int numeroCorrespondencias) {
        //Funcao que da print às correspondencias e outros
        for(int i = 0; i < paises.length; i++){
            if (matrizCorrespondencias[i] != 0) {
                System.out.println(paises[i][1] + " : " + matrizCorrespondencias[i]);
            }
        }
        if (URLs.length - numeroCorrespondencias != 0) {
            System.out.println("Outro(s): " + (URLs.length - numeroCorrespondencias));
        }
    }
    private static void verificaPaisesComDiferentesPrefixos(String[][] paises, int[] matrizCorrespondencias) {
        //Funcao que verifica se o mesmo pais tem mais que um prefixo acossiado a ele
        for (int i = 0; i < paises.length;i++){
            for(int k = i+1; k < paises.length;k++){
                if (paises[i][1].equals(paises[k][1])){
                    matrizCorrespondencias[i] += matrizCorrespondencias[k]; //Ao numero de correspondencias encontradas no i(esimo) país, adiciono o numero de correspondencias encontardas no prefixo k do mesmo país
                    matrizCorrespondencias[k] = 0; //Ponho a correspondencia k dum pais a 0 para nao dar print ao mesmo pais duas ou mais vezes.
                }
            }
        }
    }
    private static int matrizCorrespondencias(String[][] paises, String[][] retiraPontos, int[] matrizCorrespondencias) {
        // Funcao que cria uma matriz com o numero de corresopndencias associadas a cada pais
        int correspondencias = 0;
        for (int j = 0; j < paises.length;j++) {
            for (String[] retiraPonto : retiraPontos) {
                    if (retiraPonto[(retiraPonto.length)-1].equals(paises[j][0])) {
                        matrizCorrespondencias[j]++;//Se houver correspondencia, adiciono na matriz +1(correspondencia)
                    }
                }
            correspondencias += matrizCorrespondencias[j];
        }
        return correspondencias;
    }
    private static void retiraPontosEBarrasURLs(String[] URLs, String[][] retiraBarras, String[][] retiraPontos) {
        //Funcao que retira as barras para uma matrtiz e depois tira os pontos para outra matriz
        for (int i = 0;i < URLs.length;i++){
            retiraBarras[i] = URLs[i].split("/");//Crio uma matriz para armazenar as string do URLs sem "/"
        }
        for (int i = 0;i < URLs.length;i++) {
            retiraPontos[i] = retiraBarras[i][2].split("\\.");//Crio outra matriz para armazenar as strings em RetiraBarras sem pontos, ficando com uma matriz com strings sem "/" e ".".
        } //RetiraBarras[i][2] O dois é o indice que contem a string com o prefixo. Logo, tiro os pontos a essa string
    }
}
