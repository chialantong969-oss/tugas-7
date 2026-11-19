
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;

public class handsontugas7 {
    public static void main(String[] args) {

        // Menentukan lokasi file
        Path source = Paths.get("report.txt");
        Path backup = Paths.get("backup/report.txt");
        Path log = Paths.get("log.txt");

        try {
            // Menulis isi laporan ke file
            Files.write(
                source,
                List.of("Report Generated", "Status: OK"),
                StandardCharsets.UTF_8
            );

            System.out.println("Laporan berhasil dibuat.");

            // Mengecek apakah file sumber tersedia
            if (Files.exists(source)) {

                // Membuat folder backup jika belum ada
                if (Files.notExists(backup.getParent())) {
                    Files.createDirectories(backup.getParent());
                }

                // Menyalin file ke folder backup
                Files.copy(
                    source,
                    backup,
                    StandardCopyOption.REPLACE_EXISTING
                );

                System.out.println("Backup berhasil dibuat.");

                // Mencatat hasil operasi ke log.txt
                Files.write(
                    log,
                    List.of(
                        "Laporan berhasil dibuat.",
                        "File report.txt berhasil disalin ke backup/report.txt.",
                        "Backup berhasil dibuat."
                    ),
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
                );

                System.out.println("Log berhasil disimpan.");

            } else {
                System.out.println("File report.txt tidak ditemukan!");
            }

        } catch (IOException e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        }
    }
}
