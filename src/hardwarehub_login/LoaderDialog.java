
package hardwarehub_login;

import hardwarehub_main.util.IconUtil;
import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class LoaderDialog extends JDialog {
    private static final String[] PREP_TEXTS = {
        "Checking credentials...",
        "Preparing your dashboard...",
        "Loading inventory modules...",
        "Syncing with database...",
        "Applying user preferences...",
        "May God Bless you...",
        "HardwareHub is made by JCBP Solutions",
        "Almost there..."
    };
    private final JLabel lblText;
    private final Random random = new Random();
    private String customText = null;

    public LoaderDialog(Window owner) {
        this(owner, null);
    }

    public LoaderDialog(Window owner, String customText) {
        super(owner);
        this.customText = customText;
        setModalityType(ModalityType.MODELESS);
        setUndecorated(true);
        setAlwaysOnTop(true);
        setBackground(new Color(0,0,0,0));
        setLayout(new GridBagLayout());

        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(32, 32, 32, 32));

        // Loader GIF
        ImageIcon loaderIcon = IconUtil.loadIcon("Loader.gif");
        JLabel lblLoader = new JLabel(loaderIcon);
        lblLoader.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblLoader);
        panel.add(Box.createVerticalStrut(18));
        panel.repaint();

        // Loading text
        lblText = new JLabel(customText != null ? customText : randomPrepText());
        lblText.setFont(lblText.getFont().deriveFont(Font.BOLD, 18f));
        lblText.setForeground(Color.WHITE);
        lblText.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblText);

        // Rounded, semi-transparent background
        JPanel glass = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(30, 30, 30, 210));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 32, 32);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        glass.setOpaque(false);
        glass.setLayout(new BorderLayout());
        glass.add(panel, BorderLayout.CENTER);
        setContentPane(glass);
        pack();
        setLocationRelativeTo(owner);
    }

    public void showRandomText() {
        if (customText == null) {
            lblText.setText(randomPrepText());
        }
    }

    private String randomPrepText() {
        return PREP_TEXTS[random.nextInt(PREP_TEXTS.length)];
    }
} 