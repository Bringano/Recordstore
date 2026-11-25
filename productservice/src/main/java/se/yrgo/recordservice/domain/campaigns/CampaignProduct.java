package se.yrgo.recordservice.domain.campaigns;

import jakarta.persistence.*;
import se.yrgo.recordservice.domain.Product;
import se.yrgo.recordservice.domain.music.product.MusicProduct;

@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"campaign_id", "product_id"}
        )
)
public class CampaignProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "campaign_id", nullable = false)
    private Campaign campaign;
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Enumerated(EnumType.STRING)
    private DiscountType discountType;
    private int discountValue;

    public CampaignProduct() {

    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

}
