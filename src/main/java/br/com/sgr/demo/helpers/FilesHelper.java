package br.com.sgr.demo.helpers;

import org.apache.commons.lang3.SystemUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FilesHelper {
    final String AUTOR = "PEDRO HENRIQUE FERREIRA RAMOS";
    final String FILENAME = "ARQ_PROJ_1.txt";
    final String FILETITLE = "ARQUIVO_DESAFIO_ESTAGIO";
    final String TRAILLER = "TRAILLER";
    final int FIRSTLINENUMBER = 1;

    /**
     * Escreve os registros no arquivo de texto
     * @param newFile
     * @param fileWriter
     * @param printWriter
     * @param registryList
     * @throws IOException
     */
    public void writeRegistryOnFile(File newFile, FileWriter fileWriter, PrintWriter printWriter, List<Registry> registryList) throws IOException {
        StringBuilder input = new StringBuilder();

        int counter=1;
        for(Registry registry : registryList) {
            counter+=1;
            printWriter.print(registry);
            printWriter.println(counter);
        }

        printWriter.flush();
    }

    /**
     * Cria um diretório no caminho desejado, de acordo com o SO
     * @return
     */
    public File createFolder() {
        String localDate = getLocalDate();
        StringBuilder fileName = new StringBuilder();

        if (SystemUtils.IS_OS_WINDOWS) {
            fileName.append("C:\\ProjetoConductor\\").append(localDate);
        } else if (SystemUtils.IS_OS_LINUX) {
            fileName.append(SystemUtils.getUserHome()).append("/ProjetoConductor/").append(localDate);
        }

        File f = new File(String.valueOf(fileName));
        if (!f.exists()) {
            f.mkdirs();
            System.out.println("Nova pasta criada");
        }
        return f;
    }

    /**
     * Cria um arquivo de texto no diretório especificado
     * @param dir
     * @param registryList
     * @return file
     * @throws IOException
     */
    public File createFile(File dir, List<Registry> registryList) throws IOException {
        File file = new File(dir, FILENAME);

        if(file.exists() && file.isFile()){
            file.delete();
            System.out.println("Arquivo deletado");
        }

        FileWriter fileWriter = new FileWriter(file, false);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        writeHeader(file, fileWriter, printWriter);
        writeRegistryOnFile(file, fileWriter, printWriter, registryList);
        writeTrailler(file, registryList.size());

        return file;
    }

    /**
     * Escreve o 'header' do arquivo de texto com algumas informações
     * @param file
     * @param fileWriter
     * @param printWriter
     * @throws IOException
     */
    public void writeHeader(File file, FileWriter fileWriter, PrintWriter printWriter) throws IOException {
        String localDate = getLocalDate();

        StringBuilder input = new StringBuilder();
        input.append(FILETITLE).append(";");
        input.append(localDate).append(";");
        input.append(AUTOR.replaceAll(" ", "&")).append(";").append(FIRSTLINENUMBER);

        printWriter.println(input);
        printWriter.flush();
    }

    /**
     * Escreve o 'TRAILLER' no arquivo de texto
     * @param file
     * @param registrySize
     * @throws IOException
     */
    public void writeTrailler(File file, int registrySize) throws IOException {
        StringBuilder input = new StringBuilder();
        FileWriter fileWriter = new FileWriter(file, true);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        input.append(TRAILLER).append(";")
                .append(registrySize).append(";")
                .append(registrySize+2);

        printWriter.println(input);
        printWriter.flush();
        printWriter.close();
    }

    /**
     * Captura a hora local e adequa ao formato ddMMyyyy
     * @return String date
     */
    public String getLocalDate() {
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        return localDate.format(formatter);
    }
}
