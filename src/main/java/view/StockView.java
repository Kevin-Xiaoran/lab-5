package view;

import entity.Stock;
import interface_adapter.ViewManagerModel;
import javax.swing.*;
import java.awt.*;

/**
 * The View for displaying detailed information about a single stock.
 */
public class StockView extends AbstractViewWithBackButton {

    private final ViewManagerModel viewManagerModel;
    private JLabel stockSymbolLabel;
    private JLabel stockPriceLabel;
    private JLabel openPriceLabel;
    private JLabel closePriceLabel;
    private JLabel lowPriceLabel;
    private JLabel highPriceLabel;
    private JLabel volumeLabel;

    public StockView(Stock stock, ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // 创建内容面板并添加股票信息
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);

        // 初始化组件并更新股票信息
        initializeComponents(contentPanel);
        updateStock(stock);

        add(contentPanel, BorderLayout.CENTER);
    }

    private void initializeComponents(JPanel contentPanel) {

        stockSymbolLabel = createLabel("", 24, Font.BOLD);
        stockPriceLabel = createLabel("", 18, Font.PLAIN);

        JPanel stockInfoPanel = new JPanel();
        stockInfoPanel.setLayout(new BoxLayout(stockInfoPanel, BoxLayout.Y_AXIS));
        stockInfoPanel.setBackground(Color.WHITE);
        stockInfoPanel.add(stockSymbolLabel);
        stockInfoPanel.add(stockPriceLabel);

        contentPanel.add(stockInfoPanel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // 详细信息标签
        openPriceLabel = createLabel("", 16, Font.PLAIN);
        closePriceLabel = createLabel("", 16, Font.PLAIN);
        lowPriceLabel = createLabel("", 16, Font.PLAIN);
        highPriceLabel = createLabel("", 16, Font.PLAIN);
        volumeLabel = createLabel("", 16, Font.PLAIN);

        contentPanel.add(openPriceLabel);
        contentPanel.add(closePriceLabel);
        contentPanel.add(lowPriceLabel);
        contentPanel.add(highPriceLabel);
        contentPanel.add(volumeLabel);
    }

    public void updateStock(Stock stock) {
        stockSymbolLabel.setText(stock.getSymbol());
        stockPriceLabel.setText("Current Price: $" + stock.getClosePrice());
        openPriceLabel.setText("Open Price: $" + stock.getOpenPrice());
        closePriceLabel.setText("Close Price: $" + stock.getClosePrice());
        lowPriceLabel.setText("Low: $" + stock.getLow());
        highPriceLabel.setText("High: $" + stock.getHigh());
        volumeLabel.setText("Volume: " + stock.getVolume() + " M");
    }

    private JLabel createLabel(String text, int fontSize, int fontStyle) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", fontStyle, fontSize));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    @Override
    void backButtonAction() {
        viewManagerModel.setState("home view");
        viewManagerModel.firePropertyChanged();
    }
}