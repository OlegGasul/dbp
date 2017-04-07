package de.mexchange.packagingdb.common.util.transformer;

import de.mexchange.packagingdb.common.util.CollectionHelper;
import de.mexchange.packagingdb.common.util.StringHelper;
import de.mexchange.packagingdb.domain.lcp.Language;
import de.mexchange.packagingdb.domain.lcp.Status;
import de.mexchange.packagingdb.domain.*;
import de.mexchange.packagingdb.entity.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * User: Garik
 * Date: 6/17/16
 * Time: 10:43 PM
 */
public class ContainerTransformer extends GeneralTransformer {

    // region <CONTAINER>

    /**
     * Transforms Bulk into BulkEntity
     * @param dto
     * @return {@link BulkEntity}
     */
    public static BulkEntity transform(Bulk dto) {
        if (dto == null) {
            return null;
        }

        BulkEntity entity = new BulkEntity();
        entity.setId(dto.getId());

        // own data
        entity.setUnCode(dto.getUnCode());

        entity.setFrameHeight(dto.getFrameHeight());
        entity.setFrameHeightMax(dto.getFrameHeightMax());
        entity.setFrameHeightMin(dto.getFrameHeightMin());

        entity.setFrameWidth(dto.getFrameWidth());
        entity.setFrameWidthMax(dto.getFrameWidthMax());
        entity.setFrameWidthMin(dto.getFrameWidthMin());

        entity.setFrameDepth(dto.getFrameDepth());
        entity.setFrameDepthMax(dto.getFrameDepthMax());
        entity.setFrameDepthMin(dto.getFrameDepthMin());

        entity.setAngleReposeWide(dto.getAngleReposeWide());
        entity.setAngleReposeDeep(dto.getAngleReposeDeep());

        entity.setOverflowVolume(dto.getOverflowVolume());

        entity.setTotalWeight(dto.getTotalWeight());
        entity.setTotalWeightMax(dto.getTotalWeightMax());
        entity.setTotalWeightMin(dto.getTotalWeightMin());

        entity.setWeightInnerContainer(dto.getWeightInnerContainer());
        entity.setWeightInnerContainerMax(dto.getWeightInnerContainerMax());
        entity.setWeightInnerContainerMin(dto.getWeightInnerContainerMin());

        entity.setWeightFrame(dto.getWeightFrame());
        entity.setWeightFrameMax(dto.getWeightFrameMax());
        entity.setWeightFrameMin(dto.getWeightFrameMin());

        entity.setWeightPalette(dto.getWeightPalette());
        entity.setWeightPaletteMax(dto.getWeightPaletteMax());
        entity.setWeightPaletteMin(dto.getWeightPaletteMin());

        entity.setHeightVerticalOutletMax(dto.getHeightVerticalOutletMax());
        entity.setHeightVerticalOutletMin(dto.getHeightVerticalOutletMin());

        entity.setDistanceOutletLevel1Max(dto.getDistanceOutletLevel1Max());
        entity.setDistanceOutletLevel1Min(dto.getDistanceOutletLevel1Min());

        entity.setDistanceOutletLevel2Max(dto.getDistanceOutletLevel2Max());
        entity.setDistanceOutletLevel2Min(dto.getDistanceOutletLevel2Min());

        entity.setPermLock(dto.getPermLock());
        entity.setEmbossing(dto.getEmbossing());
        entity.setEmbossingPosition(dto.getEmbossingPosition());

        entity.setDrawingNumber(dto.getDrawingNumber());

        entity.setDrawing(dto.getDrawing());
        entity.setDrawingFilename(dto.getDrawingFilename());
        entity.setDrawingContentType(dto.getDrawingContentType());

        entity.setDrawingCAD(dto.getDrawingCAD());
        entity.setDrawingCADFilename(dto.getDrawingCADFilename());
        entity.setDrawingCADContentType(dto.getDrawingCADContentType());

        entity.setManufacturerSpecifications(dto.getManufacturerSpecifications());
        entity.setManufacturerSpecificationsFilename(dto.getManufacturerSpecificationsFilename());
        entity.setManufacturerSpecificationsContentType(dto.getManufacturerSpecificationsContentType());


        entity.setPalletWidth(dto.getPalletWidth());
        entity.setPalletHeight(dto.getPalletHeight());

        entity.setPaletteDepth(dto.getPaletteDepth());

        entity.setPaletteInputHeight(dto.getPaletteInputHeight());

        entity.setNozzleHeightMax(dto.getNozzleHeightMax());
        entity.setNozzleHeightMin(dto.getNozzleHeightMin());

        entity.setWallThicknessContainer(dto.getWallThicknessContainer());
        entity.setOther(dto.getOther());

        if (dto.getPaletteMaterial() != null && dto.getPaletteMaterial().getId() != null) {
            MaterialEntity materialEntity = new MaterialEntity();
            materialEntity.setId(dto.getPaletteMaterial().getId());
            entity.setPaletteMaterial(materialEntity);
        }

        if (dto.getPaletteType() != null && dto.getPaletteType().getId() != null) {
            PaletteTypeEntity paletteTypeEntity = new PaletteTypeEntity();
            paletteTypeEntity.setId(dto.getPaletteType().getId());
            entity.setPaletteType(paletteTypeEntity);
        }

        if (dto.getOutletArmatur() != null && dto.getOutletArmatur().getId() != null) {
            ClosureEntity closureEntity = new ClosureEntity();
            closureEntity.setId(dto.getOutletArmatur().getId());
            entity.setOutletArmatur(closureEntity);
        }

        if (dto.getOutletCoupling() != null && dto.getOutletCoupling().getId() != null) {
            CouplingEntity couplingEntity = new CouplingEntity();
            couplingEntity.setId(dto.getOutletCoupling().getId());
            entity.setOutletCoupling(couplingEntity);
        }

        if (dto.getContainerMaterial() != null && dto.getContainerMaterial().getId() != null) {
            MaterialEntity materialEntity = new MaterialEntity();
            materialEntity.setId(dto.getContainerMaterial().getId());
            entity.setContainerMaterial(materialEntity);
        }

        if (dto.getCoatingTank() != null && dto.getCoatingTank().getId() != null) {
            CoatingEntity coatingEntity = new CoatingEntity();
            coatingEntity.setId(dto.getCoatingTank().getId());
            entity.setCoatingTank(coatingEntity);
        }

        if (dto.getFrameMaterial() != null && dto.getFrameMaterial().getId() != null) {
            MaterialEntity materialEntity = new MaterialEntity();
            materialEntity.setId(dto.getFrameMaterial().getId());
            entity.setFrameMaterial(materialEntity);
        }

        if (dto.getExZone() != null && dto.getExZone().getId() != null) {
            ExZoneEntity exZoneEntity = new ExZoneEntity();
            exZoneEntity.setId(dto.getExZone().getId());
            entity.setExZone(exZoneEntity);
        }

        if (dto.getCoatingInside() != null && dto.getCoatingInside().getId() != null) {
            CoatingEntity coatingEntity = new CoatingEntity();
            coatingEntity.setId(dto.getCoatingInside().getId());
            entity.setCoatingInside(coatingEntity);
        }

        if (dto.getCoatingFrame() != null && dto.getCoatingFrame().getId() != null) {
            CoatingEntity coatingEntity = new CoatingEntity();
            coatingEntity.setId(dto.getCoatingFrame().getId());
            entity.setCoatingFrame(coatingEntity);
        }

        if (dto.getContainerCoating() != null && dto.getContainerCoating().getId() != null) {
            CoatingEntity coatingEntity = new CoatingEntity();
            coatingEntity.setId(dto.getContainerCoating().getId());
            entity.setContainerCoating(coatingEntity);
        }

        if (dto.getFillingOpenings() != null && dto.getFillingOpenings().getId() != null) {
            ClosureEntity closureEntity = new ClosureEntity();
            closureEntity.setId(dto.getFillingOpenings().getId());
            entity.setFillingOpenings(closureEntity);
        }

        if (dto.getFittingHorizontally1() != null && dto.getFittingHorizontally1().getId() != null) {
            ClosureEntity closureEntity = new ClosureEntity();
            closureEntity.setId(dto.getFittingHorizontally1().getId());
            entity.setFittingHorizontally1(closureEntity);
        }

        if (dto.getFittingHorizontally2() != null && dto.getFittingHorizontally2().getId() != null) {
            ClosureEntity closureEntity = new ClosureEntity();
            closureEntity.setId(dto.getFittingHorizontally2().getId());
            entity.setFittingHorizontally2(closureEntity);
        }

        if (dto.getClutchHorizontally1() != null && dto.getClutchHorizontally1().getId() != null) {
            CouplingEntity couplingEntity = new CouplingEntity();
            couplingEntity.setId(dto.getClutchHorizontally1().getId());
            entity.setClutchHorizontally1(couplingEntity);
        }

        if (dto.getClutchHorizontally2() != null && dto.getClutchHorizontally2().getId() != null) {
            CouplingEntity couplingEntity = new CouplingEntity();
            couplingEntity.setId(dto.getClutchHorizontally2().getId());
            entity.setClutchHorizontally2(couplingEntity);
        }

        if (dto.getFittingVertically() != null && dto.getFittingVertically().getId() != null) {
            ClosureEntity closureEntity = new ClosureEntity();
            closureEntity.setId(dto.getFittingVertically().getId());
            entity.setFittingVertically(closureEntity);
        }

        if (dto.getCouplingPerpendicular() != null && dto.getCouplingPerpendicular().getId() != null) {
            CouplingEntity couplingEntity = new CouplingEntity();
            couplingEntity.setId(dto.getCouplingPerpendicular().getId());
            entity.setCouplingPerpendicular(couplingEntity);
        }

        if (dto.getHole() != null && dto.getHole().getId() != null) {
            ClosureEntity closureEntity = new ClosureEntity();
            closureEntity.setId(dto.getHole().getId());
            entity.setHole(closureEntity);
        }

        transformCommonFields(entity, dto);

        fromModelToEntity(dto, entity);
        return entity;
    }
    /**
     * Transforms BulkEntity into Bulk
     * @param entity
     * @return {@link Bulk}
     */
    public static Bulk transform(BulkEntity entity) {
        return transform(entity, null);
    }

    /**
     * Transforms BulkEntity into Bulk
     * @param entity
     * @return {@link Bulk}
     */
    public static Bulk transform(BulkEntity entity, Language language) {
        if (entity == null) {
            return null;
        }

        Bulk dto = new Bulk();
        dto.setId(entity.getId());

        // own data
        dto.setUnCode(entity.getUnCode());

        dto.setFrameHeight(entity.getFrameHeight());
        dto.setFrameHeightMax(entity.getFrameHeightMax());
        dto.setFrameHeightMin(entity.getFrameHeightMin());

        dto.setFrameWidth(entity.getFrameWidth());
        dto.setFrameWidthMax(entity.getFrameWidthMax());
        dto.setFrameWidthMin(entity.getFrameWidthMin());

        dto.setFrameDepth(entity.getFrameDepth());
        dto.setFrameDepthMax(entity.getFrameDepthMax());
        dto.setFrameDepthMin(entity.getFrameDepthMin());

        dto.setAngleReposeWide(entity.getAngleReposeWide());
        dto.setAngleReposeDeep(entity.getAngleReposeDeep());

        dto.setOverflowVolume(entity.getOverflowVolume());

        dto.setTotalWeight(entity.getTotalWeight());
        dto.setTotalWeightMax(entity.getTotalWeightMax());
        dto.setTotalWeightMin(entity.getTotalWeightMin());

        dto.setWeightInnerContainer(entity.getWeightInnerContainer());
        dto.setWeightInnerContainerMax(entity.getWeightInnerContainerMax());
        dto.setWeightInnerContainerMin(entity.getWeightInnerContainerMin());

        dto.setWeightFrame(entity.getWeightFrame());
        dto.setWeightFrameMax(entity.getWeightFrameMax());
        dto.setWeightFrameMin(entity.getWeightFrameMin());

        dto.setWeightPalette(entity.getWeightPalette());
        dto.setWeightPaletteMax(entity.getWeightPaletteMax());
        dto.setWeightPaletteMin(entity.getWeightPaletteMin());

        dto.setPermLock(entity.getPermLock());
        dto.setEmbossing(entity.getEmbossing());
        dto.setEmbossingPosition(entity.getEmbossingPosition());

        dto.setDrawingNumber(entity.getDrawingNumber());

        dto.setDrawing(entity.getDrawing());
        dto.setDrawingFilename(entity.getDrawingFilename());
        dto.setDrawingContentType(entity.getDrawingContentType());

        dto.setDrawingCAD(entity.getDrawingCAD());
        dto.setDrawingCADFilename(entity.getDrawingCADFilename());
        dto.setDrawingCADContentType(entity.getDrawingCADContentType());

        dto.setManufacturerSpecifications(entity.getManufacturerSpecifications());
        dto.setManufacturerSpecificationsFilename(entity.getManufacturerSpecificationsFilename());
        dto.setManufacturerSpecificationsContentType(entity.getManufacturerSpecificationsContentType());

        dto.setPalletWidth(entity.getPalletWidth());
        dto.setPalletHeight(entity.getPalletHeight());
        dto.setPaletteDepth(entity.getPaletteDepth());

        dto.setPaletteInputHeight(entity.getPaletteInputHeight());

        dto.setNozzleHeightMax(entity.getNozzleHeightMax());
        dto.setNozzleHeightMin(entity.getNozzleHeightMin());

        dto.setHeightVerticalOutletMax(entity.getHeightVerticalOutletMax());
        dto.setHeightVerticalOutletMin(entity.getHeightVerticalOutletMin());

        dto.setDistanceOutletLevel1Max(entity.getDistanceOutletLevel1Max());
        dto.setDistanceOutletLevel1Min(entity.getDistanceOutletLevel1Min());

        dto.setDistanceOutletLevel2Max(entity.getDistanceOutletLevel2Max());
        dto.setDistanceOutletLevel2Min(entity.getDistanceOutletLevel2Min());

        dto.setWallThicknessContainer(entity.getWallThicknessContainer());
        dto.setOther(entity.getOther());

        if (entity.getFillingOpenings() != null) {
            dto.setFillingOpenings(DataTransformer.transform(entity.getFillingOpenings(), language));
        }

        if (entity.getFittingHorizontally1() != null) {
            dto.setFittingHorizontally1(DataTransformer.transform(entity.getFittingHorizontally1()));
        }

        if (entity.getFittingHorizontally2() != null) {
            dto.setFittingHorizontally2(DataTransformer.transform(entity.getFittingHorizontally2(),language));
        }

        if (entity.getClutchHorizontally1() != null) {
            dto.setClutchHorizontally1(DataTransformer.transform(entity.getClutchHorizontally1(), language));
        }

        if (entity.getClutchHorizontally2() != null) {
            dto.setClutchHorizontally2(DataTransformer.transform(entity.getClutchHorizontally2(), language));
        }

        if (entity.getFittingVertically() != null) {
            dto.setFittingVertically(DataTransformer.transform(entity.getFittingVertically(), language));
        }

        if (entity.getCouplingPerpendicular() != null) {
            dto.setCouplingPerpendicular(DataTransformer.transform(entity.getCouplingPerpendicular(), language));
        }

        if (entity.getPaletteMaterial() != null) {
            dto.setPaletteMaterial(DataTransformer.transform(entity.getPaletteMaterial(), language));
        }

        if (entity.getPaletteType() != null) {
            dto.setPaletteType(DataTransformer.transform(entity.getPaletteType(), language));
        }

        if (entity.getOutletArmatur() != null) {
            dto.setOutletArmatur(DataTransformer.transform(entity.getOutletArmatur(), language));
        }

        if (entity.getOutletCoupling() != null) {
            dto.setOutletCoupling(DataTransformer.transform(entity.getOutletCoupling(), language));
        }

        if (entity.getContainerMaterial() != null) {
            dto.setContainerMaterial(DataTransformer.transform(entity.getContainerMaterial(), language));
        }

        if (entity.getCoatingTank() != null) {
            dto.setCoatingTank(DataTransformer.transform(entity.getCoatingTank(), language));
        }

        if (entity.getFrameMaterial() != null) {
            dto.setFrameMaterial(DataTransformer.transform(entity.getFrameMaterial(), language));
        }

        if (entity.getExZone() != null) {
            dto.setExZone(DataTransformer.transform(entity.getExZone(), language));
        }

        if (entity.getCoatingInside() != null) {
            dto.setCoatingInside(DataTransformer.transform(entity.getCoatingInside(), language));
        }

        if (entity.getContainerCoating() != null) {
            dto.setContainerCoating(DataTransformer.transform(entity.getContainerCoating(), language));
        }

        if (entity.getCoatingFrame() != null) {
            dto.setCoatingFrame(DataTransformer.transform(entity.getCoatingFrame(), language));
        }

        if (entity.getHole() != null) {
            dto.setHole(DataTransformer.transform(entity.getHole(), language));
        }

        transformCommonFields(dto, entity, language);
        return dto;
    }

    /**
     * Transforms Cubic into CubicEntity
     * @param dto
     * @return {@link CubicEntity}
     */
    public static CubicEntity transform(Cubic dto) {
        if (dto == null) {
            return null;
        }

        CubicEntity entity = new CubicEntity();
        entity.setId(dto.getId());

        // own data
        entity.setOverflowVolume(dto.getOverflowVolume())
                .setUnCode(dto.getUnCode());

        if (dto.getExZone() != null && dto.getExZone().getId() != null) {
            ExZoneEntity exZoneEntity = new ExZoneEntity();
            exZoneEntity.setId(dto.getExZone().getId());
            entity.setExZone(exZoneEntity);
        }

        entity.setPermLock(dto.getPermLock())
                .setEmbossing(dto.getEmbossing())
                .setEmbossingPosition(dto.getEmbossingPosition());

        if (dto.getCoatingInside() != null && dto.getCoatingInside().getId() != null) {
            CoatingEntity coatingEntity = new CoatingEntity();
            coatingEntity.setId(dto.getCoatingInside().getId());
            entity.setCoatingInside(coatingEntity);
        }

        entity.setDrawingNumber(dto.getDrawingNumber());
        entity.setDrawing(dto.getDrawing())
                .setDrawingFilename(dto.getDrawingFilename())
                .setDrawingContentType(dto.getDrawingContentType())
                .setDrawingCAD(dto.getDrawingCAD())
                .setDrawingCADFilename(dto.getDrawingCADFilename())
                .setDrawingCADContentType(dto.getDrawingCADContentType())
                .setManufacturerSpecifications(dto.getManufacturerSpecifications())
                .setManufacturerSpecificationsFilename(dto.getManufacturerSpecificationsFilename())
                .setManufacturerSpecificationsContentType(dto.getManufacturerSpecificationsContentType());

        entity.setFrameHeight(dto.getFrameHeight())
                .setFrameHeightMax(dto.getFrameHeightMax())
                .setFrameHeightMin(dto.getFrameHeightMin());

        entity.setFrameWidth(dto.getFrameWidth())
                .setFrameWidthMax(dto.getFrameWidthMax())
                .setFrameWidthMin(dto.getFrameWidthMin());

        entity.setFrameDepth(dto.getFrameDepth())
                .setFrameDepthMax(dto.getFrameDepthMax())
                .setFrameDepthMin(dto.getFrameDepthMin());

        entity.setWallThicknessContainer(dto.getWallThicknessContainer())
                .setWallThicknessContainerMax(dto.getWallThicknessContainerMax())
                .setWallThicknessContainerMin(dto.getWallThicknessContainerMin());

        entity.setTotalWeight(dto.getTotalWeight())
                .setTotalWeightMax(dto.getTotalWeightMax())
                .setTotalWeightMin(dto.getTotalWeightMin());

        entity.setWeightInnerContainer(dto.getWeightInnerContainer())
                .setWeightInnerContainerMax(dto.getWeightInnerContainerMax())
                .setWeightInnerContainerMin(dto.getWeightInnerContainerMin());

        entity.setWeightFrame(dto.getWeightFrame())
                .setWeightFrameMax(dto.getWeightFrameMax())
                .setWeightFrameMin(dto.getWeightFrameMin());

        entity.setWeightPalette(dto.getWeightPalette())
                .setWeightPaletteMax(dto.getWeightPaletteMax())
                .setWeightPaletteMin(dto.getWeightPaletteMin());

        entity.setPalletHeight(dto.getPalletHeight())
                .setPalletWidth(dto.getPalletWidth());

        entity.setPaletteDepth(dto.getPaletteDepth());

        entity.setWeightPalette(dto.getWeightPalette())
                .setWeightPaletteMax(dto.getWeightPaletteMax())
                .setWeightPaletteMin(dto.getWeightPaletteMin());

        entity.setPaletteInputHeight(dto.getPaletteInputHeight());

        entity.setNozzleHeightMax(dto.getNozzleHeightMax())
                .setNozzleHeightMin(dto.getNozzleHeightMin());

        if (dto.getPaletteMaterial() != null && dto.getPaletteMaterial().getId() != null) {
            MaterialEntity materialEntity = new MaterialEntity();
            materialEntity.setId(dto.getPaletteMaterial().getId());
            entity.setPaletteMaterial(materialEntity);
        }

        if (dto.getPaletteType() != null && dto.getPaletteType().getId() != null) {
            PaletteTypeEntity paletteTypeEntity = new PaletteTypeEntity();
            paletteTypeEntity.setId(dto.getPaletteType().getId());
            entity.setPaletteType(paletteTypeEntity);
        }

        if (dto.getCoatingFrame() != null && dto.getCoatingFrame().getId() != null) {
            CoatingEntity coatingEntity = new CoatingEntity();
            coatingEntity.setId(dto.getCoatingFrame().getId());
            entity.setCoatingFrame(coatingEntity);
        }

        if (dto.getFrameMaterial() != null && dto.getFrameMaterial().getId() != null) {
            MaterialEntity materialEntity = new MaterialEntity();
            materialEntity.setId(dto.getFrameMaterial().getId());
            entity.setFrameMaterial(materialEntity);
        }

        if (dto.getContainerMaterial() != null && dto.getContainerMaterial().getId() != null) {
            MaterialEntity materialEntity = new MaterialEntity();
            materialEntity.setId(dto.getContainerMaterial().getId());
            entity.setContainerMaterial(materialEntity);
        }

        if (dto.getOutletArmatur() != null && dto.getOutletArmatur().getId() != null) {
            ClosureEntity closureEntity = new ClosureEntity();
            closureEntity.setId(dto.getOutletArmatur().getId());
            entity.setOutletArmatur(closureEntity);
        }

        if (dto.getOutletCoupling() != null && dto.getOutletCoupling().getId() != null) {
            CouplingEntity couplingEntity = new CouplingEntity();
            couplingEntity.setId(dto.getOutletCoupling().getId());
            entity.setOutletCoupling(couplingEntity);
        }

        if (dto.getHole() != null && dto.getHole().getId() != null) {
            ClosureEntity closureEntity = new ClosureEntity();
            closureEntity.setId(dto.getHole().getId());
            entity.setHole(closureEntity);
        }

        if (dto.getCoatingTank() != null && dto.getCoatingTank().getId() != null) {
            CoatingEntity coatingEntity = new CoatingEntity();
            coatingEntity.setId(dto.getCoatingTank().getId());
            entity.setCoatingTank(coatingEntity);
        }

        entity.setOther(dto.getOther());

        transformCommonFields(entity, dto);

        fromModelToEntity(dto, entity);
        return entity;
    }

    /**
     * Transforms CubicEntity into Cubic
     * @param entity
     * @return {@link Cubic}
     */
    public static Cubic transform(CubicEntity entity) {
        return transform(entity, null);
    }

    /**
     * Transforms CubicEntity into Cubic
     * @param entity
     * @return {@link Cubic}
     */
    public static Cubic transform(CubicEntity entity, Language language) {
        if (entity == null) {
            return null;
        }

        Cubic dto = new Cubic();
        dto.setId(entity.getId());

        // own data
        dto.setOverflowVolume(entity.getOverflowVolume());
        dto.setUnCode(entity.getUnCode());

        if (entity.getExZone() != null) {
            dto.setExZone(DataTransformer.transform(entity.getExZone()));
        }

        dto.setPermLock(entity.getPermLock());
        dto.setEmbossing(entity.getEmbossing());
        dto.setEmbossingPosition(entity.getEmbossingPosition());

        if (entity.getCoatingInside() != null) {
            dto.setCoatingInside(DataTransformer.transform(entity.getCoatingInside(), language));
        }

        dto.setDrawingNumber(entity.getDrawingNumber());

        dto.setDrawing(entity.getDrawing());
        dto.setDrawingFilename(entity.getDrawingFilename());
        dto.setDrawingContentType(entity.getDrawingContentType());

        dto.setDrawingCAD(entity.getDrawingCAD());
        dto.setDrawingCADFilename(entity.getDrawingCADFilename());
        dto.setDrawingCADContentType(entity.getDrawingCADContentType());

        dto.setManufacturerSpecifications(entity.getManufacturerSpecifications());
        dto.setManufacturerSpecificationsFilename(entity.getManufacturerSpecificationsFilename());
        dto.setManufacturerSpecificationsContentType(entity.getManufacturerSpecificationsContentType());

        dto.setFrameHeight(entity.getFrameHeight());
        dto.setFrameHeightMax(entity.getFrameHeightMax());
        dto.setFrameHeightMin(entity.getFrameHeightMin());

        dto.setFrameWidth(entity.getFrameWidth());
        dto.setFrameWidthMax(entity.getFrameWidthMax());
        dto.setFrameWidthMin(entity.getFrameWidthMin());

        dto.setFrameDepth(entity.getFrameDepth());
        dto.setFrameDepthMax(entity.getFrameDepthMax());
        dto.setFrameDepthMin(entity.getFrameDepthMin());

        dto.setWallThicknessContainer(entity.getWallThicknessContainer());
        dto.setWallThicknessContainerMax(entity.getWallThicknessContainerMax());
        dto.setWallThicknessContainerMin(entity.getWallThicknessContainerMin());

        dto.setTotalWeight(entity.getTotalWeight());
        dto.setTotalWeightMax(entity.getTotalWeightMax());
        dto.setTotalWeightMin(entity.getTotalWeightMin());

        dto.setWeightInnerContainer(entity.getWeightInnerContainer());
        dto.setWeightInnerContainerMax(entity.getWeightInnerContainerMax());
        dto.setWeightInnerContainerMin(entity.getWeightInnerContainerMin());

        dto.setWeightFrame(entity.getWeightFrame());
        dto.setWeightFrameMax(entity.getWeightFrameMax());
        dto.setWeightFrameMin(entity.getWeightFrameMin());

        dto.setWeightPalette(entity.getWeightPalette());
        dto.setWeightPaletteMax(entity.getWeightPaletteMax());
        dto.setWeightPaletteMin(entity.getWeightPaletteMin());

        dto.setPalletHeight(entity.getPalletHeight());
        dto.setPalletWidth(entity.getPalletWidth());
        dto.setPaletteDepth(entity.getPaletteDepth());

        dto.setWeightPalette(entity.getWeightPalette());
        dto.setWeightPaletteMax(entity.getWeightPaletteMax());
        dto.setWeightPaletteMin(entity.getWeightPaletteMin());

        dto.setPaletteInputHeight(entity.getPaletteInputHeight());

        dto.setNozzleHeightMax(entity.getNozzleHeightMax());
        dto.setNozzleHeightMin(entity.getNozzleHeightMin());

        if (entity.getPaletteMaterial() != null) {
            dto.setPaletteMaterial(DataTransformer.transform(entity.getPaletteMaterial(), language));
        }

        if (entity.getPaletteType() != null) {
            dto.setPaletteType(DataTransformer.transform(entity.getPaletteType(), language));
        }

        if (entity.getCoatingFrame() != null) {
            dto.setCoatingFrame(DataTransformer.transform(entity.getCoatingFrame(), language));
        }

        if (entity.getFrameMaterial() != null) {
            dto.setFrameMaterial(DataTransformer.transform(entity.getFrameMaterial(), language));
        }

        if (entity.getContainerMaterial() != null) {
            dto.setContainerMaterial(DataTransformer.transform(entity.getContainerMaterial(), language));
        }

        if (entity.getOutletArmatur() != null) {
            dto.setOutletArmatur(DataTransformer.transform(entity.getOutletArmatur(), language));
        }

        if (entity.getOutletCoupling() != null) {
            dto.setOutletCoupling(DataTransformer.transform(entity.getOutletCoupling(), language));
        }

        if (entity.getHole() != null) {
            dto.setHole(DataTransformer.transform(entity.getHole(), language));
        }

        if (entity.getCoatingTank() != null) {
            dto.setCoatingTank(DataTransformer.transform(entity.getCoatingTank(), language));
        }

        dto.setOther(entity.getOther());

        transformCommonFields(dto, entity, language);

        fromEntityToModel(entity, dto);
        return dto;
    }

    /**
     * Transforms Cylindrical into CylindricalEntity
     * @param dto
     * @return {@link CylindricalEntity}
     */
    public static CylindricalEntity transform(Cylindrical dto) {
        if (dto == null) {
            return null;
        }

        CylindricalEntity entity = new CylindricalEntity();
        entity.setId(dto.getId());

        // own data
        entity.setOverflowVolume(dto.getOverflowVolume())
                .setUnCode(dto.getUnCode());

        if (dto.getExZone() != null && dto.getExZone().getId() != null) {
            ExZoneEntity exZoneEntity = new ExZoneEntity();
            exZoneEntity.setId(dto.getExZone().getId());
            entity.setExZone(exZoneEntity);
        }

        entity.setPermLock(dto.getPermLock())
                .setEmbossing(dto.getEmbossing())
                .setEmbossingPosition(dto.getEmbossingPosition());

        if (dto.getCoatingInside() != null && dto.getCoatingInside().getId() != null) {
            CoatingEntity coatingEntity = new CoatingEntity();
            coatingEntity.setId(dto.getCoatingInside().getId());
            entity.setCoatingInside(coatingEntity);
        }

        entity.setDrawingNumber(dto.getDrawingNumber());
        entity.setDrawing(dto.getDrawing())
                .setDrawingFilename(dto.getDrawingFilename())
                .setDrawingContentType(dto.getDrawingContentType())
                .setDrawingCAD(dto.getDrawingCAD())
                .setDrawingCADFilename(dto.getDrawingCADFilename())
                .setDrawingCADContentType(dto.getDrawingCADContentType())
                .setManufacturerSpecifications(dto.getManufacturerSpecifications())
                .setManufacturerSpecificationsFilename(dto.getManufacturerSpecificationsFilename())
                .setManufacturerSpecificationsContentType(dto.getManufacturerSpecificationsContentType());

        entity.setFrameHeight(dto.getFrameHeight())
                .setFrameHeightMax(dto.getFrameHeightMax())
                .setFrameHeightMin(dto.getFrameHeightMin());

        entity.setFrameWidth(dto.getFrameWidth())
                .setFrameWidthMax(dto.getFrameWidthMax())
                .setFrameWidthMin(dto.getFrameWidthMin());

        entity.setFrameDepth(dto.getFrameDepth())
                .setFrameDepthMax(dto.getFrameDepthMax())
                .setFrameDepthMin(dto.getFrameDepthMin());

        entity.setDiameterContainer(dto.getDiameterContainer())
                .setDiameterContainerMax(dto.getDiameterContainerMax())
                .setDiameterContainerMin(dto.getDiameterContainerMin());

        entity.setWallThicknessContainer(dto.getWallThicknessContainer())
                .setWallThicknessContainerMax(dto.getWallThicknessContainerMax())
                .setWallThicknessContainerMin(dto.getWallThicknessContainerMin());

        entity.setCapDiameter(dto.getCapDiameter())
                .setCapDiameterMax(dto.getCapDiameterMax())
                .setCapDiameterMin(dto.getCapDiameterMin());

        entity.setTotalWeight(dto.getTotalWeight())
                .setTotalWeightMax(dto.getTotalWeightMax())
                .setTotalWeightMin(dto.getTotalWeightMin());

        entity.setWeightInnerContainer(dto.getWeightInnerContainer())
                .setWeightInnerContainerMax(dto.getWeightInnerContainerMax())
                .setWeightInnerContainerMin(dto.getWeightInnerContainerMin());

        entity.setWeightFrame(dto.getWeightFrame())
                .setWeightFrameMax(dto.getWeightFrameMax())
                .setWeightFrameMin(dto.getWeightFrameMin());

        entity.setWeightPalette(dto.getWeightPalette())
                .setWeightPaletteMax(dto.getWeightPaletteMax())
                .setWeightPaletteMin(dto.getWeightPaletteMin());

        entity.setPalletHeight(dto.getPalletHeight())
                .setPalletWidth(dto.getPalletWidth());

        entity.setPaletteDepth(dto.getPaletteDepth());

        entity.setWeightPalette(dto.getWeightPalette())
                .setWeightPaletteMax(dto.getWeightPaletteMax())
                .setWeightPaletteMin(dto.getWeightPaletteMin());

        entity.setPaletteInputHeight(dto.getPaletteInputHeight());

        entity.setNozzleHeightMax(dto.getNozzleHeightMax())
                .setNozzleHeightMin(dto.getNozzleHeightMin());

        if (dto.getPaletteMaterial() != null && dto.getPaletteMaterial().getId() != null) {
            MaterialEntity materialEntity = new MaterialEntity();
            materialEntity.setId(dto.getPaletteMaterial().getId());
            entity.setPaletteMaterial(materialEntity);
        }

        if (dto.getPaletteType() != null && dto.getPaletteType().getId() != null) {
            PaletteTypeEntity paletteTypeEntity = new PaletteTypeEntity();
            paletteTypeEntity.setId(dto.getPaletteType().getId());
            entity.setPaletteType(paletteTypeEntity);
        }

        if (dto.getCoatingFrame() != null && dto.getCoatingFrame().getId() != null) {
            CoatingEntity coatingEntity = new CoatingEntity();
            coatingEntity.setId(dto.getCoatingFrame().getId());
            entity.setCoatingFrame(coatingEntity);
        }

        if (dto.getFrameMaterial() != null && dto.getFrameMaterial().getId() != null) {
            MaterialEntity materialEntity = new MaterialEntity();
            materialEntity.setId(dto.getFrameMaterial().getId());
            entity.setFrameMaterial(materialEntity);
        }

        if (dto.getContainerMaterial() != null && dto.getContainerMaterial().getId() != null) {
            MaterialEntity materialEntity = new MaterialEntity();
            materialEntity.setId(dto.getContainerMaterial().getId());
            entity.setContainerMaterial(materialEntity);
        }

        if (dto.getOutletArmatur() != null && dto.getOutletArmatur().getId() != null) {
            ClosureEntity closureEntity = new ClosureEntity();
            closureEntity.setId(dto.getOutletArmatur().getId());
            entity.setOutletArmatur(closureEntity);
        }

        if (dto.getOutletCoupling() != null && dto.getOutletCoupling().getId() != null) {
            CouplingEntity couplingEntity = new CouplingEntity();
            couplingEntity.setId(dto.getOutletCoupling().getId());
            entity.setOutletCoupling(couplingEntity);
        }

        if (dto.getHole() != null && dto.getHole().getId() != null) {
            ClosureEntity closureEntity = new ClosureEntity();
            closureEntity.setId(dto.getHole().getId());
            entity.setHole(closureEntity);
        }

        if (dto.getCoatingTank() != null && dto.getCoatingTank().getId() != null) {
            CoatingEntity coatingEntity = new CoatingEntity();
            coatingEntity.setId(dto.getCoatingTank().getId());
            entity.setCoatingTank(coatingEntity);
        }

        entity.setOther(dto.getOther());

        transformCommonFields(entity, dto);

        fromModelToEntity(dto, entity);
        return entity;
    }

    /**
     * Transforms CylindricalEntity into Cylindrical
     * @param entity
     * @return {@link Cylindrical}
     */
    public static Cylindrical transform(CylindricalEntity entity) {
        return transform(entity, null);
    }

    /**
     * Transforms CylindricalEntity into Cylindrical
     * @param entity
     * @return {@link Cylindrical}
     */
    public static Cylindrical transform(CylindricalEntity entity, Language language) {
        if (entity == null) {
            return null;
        }

        Cylindrical dto = new Cylindrical();
        dto.setId(entity.getId());

        // own data
        dto.setOverflowVolume(entity.getOverflowVolume());
        dto.setUnCode(entity.getUnCode());

        if (entity.getExZone() != null) {
            dto.setExZone(DataTransformer.transform(entity.getExZone(), language));
        }

        dto.setPermLock(entity.getPermLock());
        dto.setEmbossing(entity.getEmbossing());
        dto.setEmbossingPosition(entity.getEmbossingPosition());

        if (entity.getCoatingInside() != null) {
            dto.setCoatingInside(DataTransformer.transform(entity.getCoatingInside(), language));
        }

        dto.setDrawingNumber(entity.getDrawingNumber());

        dto.setDrawing(entity.getDrawing());
        dto.setDrawingFilename(entity.getDrawingFilename());
        dto.setDrawingContentType(entity.getDrawingContentType());

        dto.setDrawingCAD(entity.getDrawingCAD());
        dto.setDrawingCADFilename(entity.getDrawingCADFilename());
        dto.setDrawingCADContentType(entity.getDrawingCADContentType());

        dto.setManufacturerSpecifications(entity.getManufacturerSpecifications());
        dto.setManufacturerSpecificationsFilename(entity.getManufacturerSpecificationsFilename());
        dto.setManufacturerSpecificationsContentType(entity.getManufacturerSpecificationsContentType());

        dto.setFrameHeight(entity.getFrameHeight());
        dto.setFrameHeightMax(entity.getFrameHeightMax());
        dto.setFrameHeightMin(entity.getFrameHeightMin());

        dto.setFrameWidth(entity.getFrameWidth());
        dto.setFrameWidthMax(entity.getFrameWidthMax());
        dto.setFrameWidthMin(entity.getFrameWidthMin());

        dto.setFrameDepth(entity.getFrameDepth());
        dto.setFrameDepthMax(entity.getFrameDepthMax());
        dto.setFrameDepthMin(entity.getFrameDepthMin());

        dto.setDiameterContainer(entity.getDiameterContainer());
        dto.setDiameterContainerMax(entity.getDiameterContainerMax());
        dto.setDiameterContainerMin(entity.getDiameterContainerMin());

        dto.setWallThicknessContainer(entity.getWallThicknessContainer());
        dto.setWallThicknessContainerMax(entity.getWallThicknessContainerMax());
        dto.setWallThicknessContainerMin(entity.getWallThicknessContainerMin());

        dto.setCapDiameter(entity.getCapDiameter());
        dto.setCapDiameterMax(entity.getCapDiameterMax());
        dto.setCapDiameterMin(entity.getCapDiameterMin());

        dto.setTotalWeight(entity.getTotalWeight());
        dto.setTotalWeightMax(entity.getTotalWeightMax());
        dto.setTotalWeightMin(entity.getTotalWeightMin());

        dto.setWeightInnerContainer(entity.getWeightInnerContainer());
        dto.setWeightInnerContainerMax(entity.getWeightInnerContainerMax());
        dto.setWeightInnerContainerMin(entity.getWeightInnerContainerMin());

        dto.setWeightFrame(entity.getWeightFrame());
        dto.setWeightFrameMax(entity.getWeightFrameMax());
        dto.setWeightFrameMin(entity.getWeightFrameMin());

        dto.setWeightPalette(entity.getWeightPalette());
        dto.setWeightPaletteMax(entity.getWeightPaletteMax());
        dto.setWeightPaletteMin(entity.getWeightPaletteMin());

        dto.setPalletHeight(entity.getPalletHeight());
        dto.setPalletWidth(entity.getPalletWidth());
        dto.setPaletteDepth(entity.getPaletteDepth());

        dto.setWeightPalette(entity.getWeightPalette());
        dto.setWeightPaletteMax(entity.getWeightPaletteMax());
        dto.setWeightPaletteMin(entity.getWeightPaletteMin());

        dto.setPaletteInputHeight(entity.getPaletteInputHeight());

        dto.setNozzleHeightMax(entity.getNozzleHeightMax());
        dto.setNozzleHeightMin(entity.getNozzleHeightMin());

        if (entity.getPaletteMaterial() != null) {
            dto.setPaletteMaterial(DataTransformer.transform(entity.getPaletteMaterial(), language));
        }

        if (entity.getPaletteType() != null) {
            dto.setPaletteType(DataTransformer.transform(entity.getPaletteType(), language));
        }

        if (entity.getCoatingFrame() != null) {
            dto.setCoatingFrame(DataTransformer.transform(entity.getCoatingFrame(), language));
        }

        if (entity.getFrameMaterial() != null) {
            dto.setFrameMaterial(DataTransformer.transform(entity.getFrameMaterial(), language));
        }

        if (entity.getContainerMaterial() != null) {
            dto.setContainerMaterial(DataTransformer.transform(entity.getContainerMaterial(), language));
        }

        if (entity.getOutletArmatur() != null) {
            dto.setOutletArmatur(DataTransformer.transform(entity.getOutletArmatur(), language));
        }

        if (entity.getOutletCoupling() != null) {
            dto.setOutletCoupling(DataTransformer.transform(entity.getOutletCoupling(), language));
        }

        if (entity.getHole() != null) {
            dto.setHole(DataTransformer.transform(entity.getHole(), language));
        }

        if (entity.getCoatingTank() != null) {
            dto.setCoatingTank(DataTransformer.transform(entity.getCoatingTank(), language));
        }

        dto.setOther(entity.getOther());

        transformCommonFields(dto, entity, language);

        fromEntityToModel(entity, dto);
        return dto;
    }

    /**
     * Transforms BigBags into BigBagsEntity
     * @param dto
     * @return {@link BigBagsEntity}
     */
    public static BigBagsEntity transform(BigBags dto) {
        if (dto == null) {
            return null;
        }

        BigBagsEntity entity = new BigBagsEntity();
        entity.setId(dto.getId());

        // own data
        entity.setOverflowVolume(dto.getOverflowVolume())
                .setUnCode(dto.getUnCode());

        if (dto.getExZone() != null && dto.getExZone().getId() != null) {
            ExZoneEntity exZoneEntity = new ExZoneEntity();
            exZoneEntity.setId(dto.getExZone().getId());
            entity.setExZone(exZoneEntity);
        }

        entity.setPermLock(dto.getPermLock())
                .setEmbossing(dto.getEmbossing())
                .setEmbossingPosition(dto.getEmbossingPosition());

        if (dto.getCoatingInside() != null && dto.getCoatingInside().getId() != null) {
            CoatingEntity coatingEntity = new CoatingEntity();
            coatingEntity.setId(dto.getCoatingInside().getId());
            entity.setCoatingInside(coatingEntity);
        }

        if (dto.getCoatingOutside() != null && dto.getCoatingOutside().getId() != null) {
            CoatingEntity coatingEntity = new CoatingEntity();
            coatingEntity.setId(dto.getCoatingOutside().getId());
            entity.setCoatingOutside(coatingEntity);
        }

        entity.setWallThicknessContainer(dto.getWallThicknessContainer());
        entity.setDrawingNumber(dto.getDrawingNumber());
        entity.setDrawing(dto.getDrawing())
                .setDrawingFilename(dto.getDrawingFilename())
                .setDrawingContentType(dto.getDrawingContentType())
                .setDrawingCAD(dto.getDrawingCAD())
                .setDrawingCADFilename(dto.getDrawingCADFilename())
                .setDrawingCADContentType(dto.getDrawingCADContentType())
                .setManufacturerSpecifications(dto.getManufacturerSpecifications())
                .setManufacturerSpecificationsFilename(dto.getManufacturerSpecificationsFilename())
                .setManufacturerSpecificationsContentType(dto.getManufacturerSpecificationsContentType());

        entity.setFrameHeight(dto.getFrameHeight())
                .setFrameHeightMax(dto.getFrameHeightMax())
                .setFrameHeightMin(dto.getFrameHeightMin());

        entity.setFrameWidth(dto.getFrameWidth())
                .setFrameWidthMax(dto.getFrameWidthMax())
                .setFrameWidthMin(dto.getFrameWidthMin());

        entity.setFrameDepth(dto.getFrameDepth())
                .setFrameDepthMax(dto.getFrameDepthMax())
                .setFrameDepthMin(dto.getFrameDepthMin());

        entity.setTotalWeight(dto.getTotalWeight())
                .setTotalWeightMax(dto.getTotalWeightMax())
                .setTotalWeightMin(dto.getTotalWeightMin());

        entity.setWeightInnerContainer(dto.getWeightInnerContainer())
                .setWeightInnerContainerMax(dto.getWeightInnerContainerMax())
                .setWeightInnerContainerMin(dto.getWeightInnerContainerMin());

        entity.setWeightFrame(dto.getWeightFrame())
                .setWeightFrameMax(dto.getWeightFrameMax())
                .setWeightFrameMin(dto.getWeightFrameMin());

        entity.setWeightPalette(dto.getWeightPalette())
                .setWeightPaletteMax(dto.getWeightPaletteMax())
                .setWeightPaletteMin(dto.getWeightPaletteMin());

        entity.setPalletHeight(dto.getPalletHeight())
                .setPalletWidth(dto.getPalletWidth());

        entity.setPaletteDepth(dto.getPaletteDepth());

        entity.setWeightPalette(dto.getWeightPalette())
                .setWeightPaletteMax(dto.getWeightPaletteMax())
                .setWeightPaletteMin(dto.getWeightPaletteMin());

        entity.setPaletteInputHeight(dto.getPaletteInputHeight());

        entity.setNozzleHeightMax(dto.getNozzleHeightMax())
                .setNozzleHeightMin(dto.getNozzleHeightMin());

        if (dto.getPaletteMaterial() != null && dto.getPaletteMaterial().getId() != null) {
            MaterialEntity materialEntity = new MaterialEntity();
            materialEntity.setId(dto.getPaletteMaterial().getId());
            entity.setPaletteMaterial(materialEntity);
        }

        if (dto.getPaletteType() != null && dto.getPaletteType().getId() != null) {
            PaletteTypeEntity paletteTypeEntity = new PaletteTypeEntity();
            paletteTypeEntity.setId(dto.getPaletteType().getId());
            entity.setPaletteType(paletteTypeEntity);
        }

        if (dto.getFrameMaterial() != null && dto.getFrameMaterial().getId() != null) {
            MaterialEntity materialEntity = new MaterialEntity();
            materialEntity.setId(dto.getFrameMaterial().getId());
            entity.setFrameMaterial(materialEntity);
        }

        if (dto.getContainerMaterial() != null && dto.getContainerMaterial().getId() != null) {
            MaterialEntity materialEntity = new MaterialEntity();
            materialEntity.setId(dto.getContainerMaterial().getId());
            entity.setContainerMaterial(materialEntity);
        }

        if (dto.getOutletArmatur() != null && dto.getOutletArmatur().getId() != null) {
            ClosureEntity closureEntity = new ClosureEntity();
            closureEntity.setId(dto.getOutletArmatur().getId());
            entity.setOutletArmatur(closureEntity);
        }

        if (dto.getOutletCoupling() != null && dto.getOutletCoupling().getId() != null) {
            CouplingEntity couplingEntity = new CouplingEntity();
            couplingEntity.setId(dto.getOutletCoupling().getId());
            entity.setOutletCoupling(couplingEntity);
        }

        if (dto.getHole() != null && dto.getHole().getId() != null) {
            ClosureEntity closureEntity = new ClosureEntity();
            closureEntity.setId(dto.getHole().getId());
            entity.setHole(closureEntity);
        }

        if (dto.getCoatingTank() != null && dto.getCoatingTank().getId() != null) {
            CoatingEntity coatingEntity = new CoatingEntity();
            coatingEntity.setId(dto.getCoatingTank().getId());
            entity.setCoatingTank(coatingEntity);
        }

        entity.setOther(dto.getOther());

        transformCommonFields(entity, dto);

        fromModelToEntity(dto, entity);
        return entity;
    }

    /**
     * Transforms BigBagsEntity into Cylindrical
     * @param entity
     * @return {@link BigBags}
     */
    public static BigBags transform(BigBagsEntity entity) {
        return transform(entity, null);
    }

    /**
     * Transforms CylindricalEntity into BigBags
     * @param entity
     * @return {@link BigBags}
     */
    public static BigBags transform(BigBagsEntity entity, Language language) {
        if (entity == null) {
            return null;
        }

        BigBags dto = new BigBags();
        dto.setId(entity.getId());

        // own data
        dto.setOverflowVolume(entity.getOverflowVolume());
        dto.setUnCode(entity.getUnCode());

        if (entity.getExZone() != null) {
            dto.setExZone(DataTransformer.transform(entity.getExZone(), language));
        }

        dto.setPermLock(entity.getPermLock());
        dto.setEmbossing(entity.getEmbossing());
        dto.setEmbossingPosition(entity.getEmbossingPosition());

        if (entity.getCoatingInside() != null) {
            dto.setCoatingInside(DataTransformer.transform(entity.getCoatingInside(), language));
        }

        if (entity.getCoatingOutside() != null) {
            dto.setCoatingOutside(DataTransformer.transform(entity.getCoatingOutside(), language));
        }

        dto.setDrawingNumber(entity.getDrawingNumber());

        dto.setDrawing(entity.getDrawing());
        dto.setDrawingFilename(entity.getDrawingFilename());
        dto.setDrawingContentType(entity.getDrawingContentType());

        dto.setDrawingCAD(entity.getDrawingCAD());
        dto.setDrawingCADFilename(entity.getDrawingCADFilename());
        dto.setDrawingCADContentType(entity.getDrawingCADContentType());

        dto.setManufacturerSpecifications(entity.getManufacturerSpecifications());
        dto.setManufacturerSpecificationsFilename(entity.getManufacturerSpecificationsFilename());
        dto.setManufacturerSpecificationsContentType(entity.getManufacturerSpecificationsContentType());

        dto.setFrameHeight(entity.getFrameHeight());
        dto.setFrameHeightMax(entity.getFrameHeightMax());
        dto.setFrameHeightMin(entity.getFrameHeightMin());

        dto.setFrameWidth(entity.getFrameWidth());
        dto.setFrameWidthMax(entity.getFrameWidthMax());
        dto.setFrameWidthMin(entity.getFrameWidthMin());

        dto.setFrameDepth(entity.getFrameDepth());
        dto.setFrameDepthMax(entity.getFrameDepthMax());
        dto.setFrameDepthMin(entity.getFrameDepthMin());

        dto.setWallThicknessContainer(entity.getWallThicknessContainer());

        dto.setTotalWeight(entity.getTotalWeight());
        dto.setTotalWeightMax(entity.getTotalWeightMax());
        dto.setTotalWeightMin(entity.getTotalWeightMin());

        dto.setWeightInnerContainer(entity.getWeightInnerContainer());
        dto.setWeightInnerContainerMax(entity.getWeightInnerContainerMax());
        dto.setWeightInnerContainerMin(entity.getWeightInnerContainerMin());

        dto.setWeightFrame(entity.getWeightFrame());
        dto.setWeightFrameMax(entity.getWeightFrameMax());
        dto.setWeightFrameMin(entity.getWeightFrameMin());

        dto.setWeightPalette(entity.getWeightPalette());
        dto.setWeightPaletteMax(entity.getWeightPaletteMax());
        dto.setWeightPaletteMin(entity.getWeightPaletteMin());

        dto.setPalletHeight(entity.getPalletHeight());
        dto.setPalletWidth(entity.getPalletWidth());
        dto.setPaletteDepth(entity.getPaletteDepth());

        dto.setWeightPalette(entity.getWeightPalette());
        dto.setWeightPaletteMax(entity.getWeightPaletteMax());
        dto.setWeightPaletteMin(entity.getWeightPaletteMin());

        dto.setPaletteInputHeight(entity.getPaletteInputHeight());

        dto.setNozzleHeightMax(entity.getNozzleHeightMax());
        dto.setNozzleHeightMin(entity.getNozzleHeightMin());

        if (entity.getPaletteMaterial() != null) {
            dto.setPaletteMaterial(DataTransformer.transform(entity.getPaletteMaterial(), language));
        }

        if (entity.getPaletteType() != null) {
            dto.setPaletteType(DataTransformer.transform(entity.getPaletteType(), language));
        }

        if (entity.getFrameMaterial() != null) {
            dto.setFrameMaterial(DataTransformer.transform(entity.getFrameMaterial(), language));
        }

        if (entity.getContainerMaterial() != null) {
            dto.setContainerMaterial(DataTransformer.transform(entity.getContainerMaterial(), language));
        }

        if (entity.getOutletArmatur() != null) {
            dto.setOutletArmatur(DataTransformer.transform(entity.getOutletArmatur(), language));
        }

        if (entity.getOutletCoupling() != null) {
            dto.setOutletCoupling(DataTransformer.transform(entity.getOutletCoupling(), language));
        }

        if (entity.getHole() != null) {
            dto.setHole(DataTransformer.transform(entity.getHole(), language));
        }

        if (entity.getCoatingTank() != null) {
            dto.setCoatingTank(DataTransformer.transform(entity.getCoatingTank(), language));
        }

        dto.setOther(entity.getOther());

        transformCommonFields(dto, entity, language);

        fromEntityToModel(entity, dto);
        return dto;
    }

    /**
     * Transforms ConicalCans into ConicalCansEntity
     * @param dto
     * @return {@link ConicalCansEntity}
     */
    public static ConicalCansEntity transform(ConicalCans dto) {
        if (dto == null) {
            return null;
        }

        ConicalCansEntity entity = new ConicalCansEntity();
        entity.setId(dto.getId());

        // own data
        entity.setOverflowVolume(dto.getOverflowVolume())
                .setUnCode(dto.getUnCode());

        if (dto.getExZone() != null && dto.getExZone().getId() != null) {
            ExZoneEntity exZoneEntity = new ExZoneEntity();
            exZoneEntity.setId(dto.getExZone().getId());
            entity.setExZone(exZoneEntity);
        }

        entity.setPermLock(dto.getPermLock())
                .setEmbossing(dto.getEmbossing())
                .setEmbossingPosition(dto.getEmbossingPosition());

        if (dto.getCoatingInside() != null && dto.getCoatingInside().getId() != null) {
            CoatingEntity coatingEntity = new CoatingEntity();
            coatingEntity.setId(dto.getCoatingInside().getId());
            entity.setCoatingInside(coatingEntity);
        }

        if (dto.getCoatingOutside() != null && dto.getCoatingOutside().getId() != null) {
            CoatingEntity coatingEntity = new CoatingEntity();
            coatingEntity.setId(dto.getCoatingOutside().getId());
            entity.setCoatingOutside(coatingEntity);
        }

        entity.setDrawingNumber(dto.getDrawingNumber());
        entity.setDrawing(dto.getDrawing())
                .setDrawingFilename(dto.getDrawingFilename())
                .setDrawingContentType(dto.getDrawingContentType())
                .setDrawingCAD(dto.getDrawingCAD())
                .setDrawingCADFilename(dto.getDrawingCADFilename())
                .setDrawingCADContentType(dto.getDrawingCADContentType())
                .setManufacturerSpecifications(dto.getManufacturerSpecifications())
                .setManufacturerSpecificationsFilename(dto.getManufacturerSpecificationsFilename())
                .setManufacturerSpecificationsContentType(dto.getManufacturerSpecificationsContentType());


        entity.setOverallHeight(dto.getOverallHeight());
        entity.setOverallHeightMax(dto.getOverallHeightMax());
        entity.setOverallHeightMin(dto.getOverallHeightMin());

        entity.setHullHeight(dto.getHullHeight());
        entity.setHullHeightMax(dto.getHullHeightMax());
        entity.setHullHeightMin(dto.getHullHeightMin());

        entity.setInnerDiameterAbove(dto.getInnerDiameterAbove());
        entity.setInnerDiameterAboveMax(dto.getInnerDiameterAboveMax());
        entity.setInnerDiameterAboveMin(dto.getInnerDiameterAboveMin());

        entity.setInnerDiameterBelow(dto.getInnerDiameterBelow());
        entity.setInnerDiameterBelowMax(dto.getInnerDiameterBelowMax());
        entity.setInnerDiameterBelowMin(dto.getInnerDiameterBelowMin());

        entity.setHullWallThickness(dto.getHullWallThickness());
        entity.setHullWallThicknessMax(dto.getHullWallThicknessMax());
        entity.setHullWallThicknessMin(dto.getHullWallThicknessMin());

        entity.setLidWallThickness(dto.getLidWallThickness());
        entity.setLidWallThicknessMax(dto.getLidWallThicknessMax());
        entity.setLidWallThicknessMin(dto.getLidWallThicknessMin());

        entity.setGroundWallThickness(dto.getGroundWallThickness());
        entity.setGroundWallThicknessMax(dto.getGroundWallThicknessMax());
        entity.setGroundWallThicknessMin(dto.getGroundWallThicknessMin());

        entity.setDiameterLid(dto.getDiameterLid());
        entity.setDiameterLidMax(dto.getDiameterLidMax());
        entity.setDiameterLidMin(dto.getDiameterLidMin());

        entity.setTotalWeight(dto.getTotalWeight())
                .setTotalWeightMax(dto.getTotalWeightMax())
                .setTotalWeightMin(dto.getTotalWeightMin());

        entity.setWeightInnerContainer(dto.getWeightInnerContainer())
                .setWeightInnerContainerMax(dto.getWeightInnerContainerMax())
                .setWeightInnerContainerMin(dto.getWeightInnerContainerMin());

        if (dto.getContainerMaterial() != null && dto.getContainerMaterial().getId() != null) {
            MaterialEntity materialEntity = new MaterialEntity();
            materialEntity.setId(dto.getContainerMaterial().getId());
            entity.setContainerMaterial(materialEntity);
        }

        if (dto.getHole() != null && dto.getHole().getId() != null) {
            ClosureEntity closureEntity = new ClosureEntity();
            closureEntity.setId(dto.getHole().getId());
            entity.setHole(closureEntity);
        }

        if (dto.getHandle() != null && dto.getHandle().getId() != null) {
            HandleEntity handleEntity = new HandleEntity();
            handleEntity.setId(dto.getHandle().getId());
            entity.setHandle(handleEntity);
        }

        if (dto.getCoatingTank() != null && dto.getCoatingTank().getId() != null) {
            CoatingEntity coatingEntity = new CoatingEntity();
            coatingEntity.setId(dto.getCoatingTank().getId());
            entity.setCoatingTank(coatingEntity);
        }

        entity.setSicken(dto.getSicken());
        entity.setOther(dto.getOther());

        transformCommonFields(entity, dto);

        fromModelToEntity(dto, entity);
        return entity;
    }

    /**
     * Transforms ConicalCansEntity into ConicalCans
     * @param entity
     * @return {@link ConicalCans}
     */
    public static ConicalCans transform(ConicalCansEntity entity) {
        return transform(entity, null);
    }

    /**
     * Transforms ConicalCansEntity into ConicalCans
     * @param entity
     * @return {@link ConicalCans}
     */
    public static ConicalCans transform(ConicalCansEntity entity, Language language) {
        if (entity == null) {
            return null;
        }

        ConicalCans dto = new ConicalCans();
        dto.setId(entity.getId());

        // own data
        dto.setOverflowVolume(entity.getOverflowVolume());
        dto.setUnCode(entity.getUnCode());

        if (entity.getExZone() != null) {
            dto.setExZone(DataTransformer.transform(entity.getExZone(), language));
        }

        dto.setPermLock(entity.getPermLock());
        dto.setEmbossing(entity.getEmbossing());
        dto.setEmbossingPosition(entity.getEmbossingPosition());

        if (entity.getCoatingInside() != null) {
            dto.setCoatingInside(DataTransformer.transform(entity.getCoatingInside(), language));
        }

        if (entity.getCoatingOutside() != null) {
            dto.setCoatingOutside(DataTransformer.transform(entity.getCoatingOutside(), language));
        }

        dto.setDrawingNumber(entity.getDrawingNumber());

        dto.setDrawing(entity.getDrawing());
        dto.setDrawingFilename(entity.getDrawingFilename());
        dto.setDrawingContentType(entity.getDrawingContentType());

        dto.setDrawingCAD(entity.getDrawingCAD());
        dto.setDrawingCADFilename(entity.getDrawingCADFilename());
        dto.setDrawingCADContentType(entity.getDrawingCADContentType());

        dto.setManufacturerSpecifications(entity.getManufacturerSpecifications());
        dto.setManufacturerSpecificationsFilename(entity.getManufacturerSpecificationsFilename());
        dto.setManufacturerSpecificationsContentType(entity.getManufacturerSpecificationsContentType());

        dto.setOverallHeight(entity.getOverallHeight());
        dto.setOverallHeightMax(entity.getOverallHeightMax());
        dto.setOverallHeightMin(entity.getOverallHeightMin());

        dto.setHullHeight(entity.getHullHeight());
        dto.setHullHeightMax(entity.getHullHeightMax());
        dto.setHullHeightMin(entity.getHullHeightMin());

        dto.setInnerDiameterAbove(entity.getInnerDiameterAbove());
        dto.setInnerDiameterAboveMax(entity.getInnerDiameterAboveMax());
        dto.setInnerDiameterAboveMin(entity.getInnerDiameterAboveMin());

        dto.setInnerDiameterBelow(entity.getInnerDiameterBelow());
        dto.setInnerDiameterBelowMax(entity.getInnerDiameterBelowMax());
        dto.setInnerDiameterBelowMin(entity.getInnerDiameterBelowMin());

        dto.setHullWallThickness(entity.getHullWallThickness());
        dto.setHullWallThicknessMax(entity.getHullWallThicknessMax());
        dto.setHullWallThicknessMin(entity.getHullWallThicknessMin());

        dto.setLidWallThickness(entity.getLidWallThickness());
        dto.setLidWallThicknessMax(entity.getLidWallThicknessMax());
        dto.setLidWallThicknessMin(entity.getLidWallThicknessMin());

        dto.setGroundWallThickness(entity.getGroundWallThickness());
        dto.setGroundWallThicknessMax(entity.getGroundWallThicknessMax());
        dto.setGroundWallThicknessMin(entity.getGroundWallThicknessMin());

        dto.setDiameterLid(entity.getDiameterLid());
        dto.setDiameterLidMax(entity.getDiameterLidMax());
        dto.setDiameterLidMin(entity.getDiameterLidMin());

        dto.setTotalWeight(entity.getTotalWeight());
        dto.setTotalWeightMax(entity.getTotalWeightMax());
        dto.setTotalWeightMin(entity.getTotalWeightMin());

        dto.setWeightInnerContainer(entity.getWeightInnerContainer());
        dto.setWeightInnerContainerMax(entity.getWeightInnerContainerMax());
        dto.setWeightInnerContainerMin(entity.getWeightInnerContainerMin());

        if (entity.getContainerMaterial() != null) {
            dto.setContainerMaterial(DataTransformer.transform(entity.getContainerMaterial(), language));
        }

        if (entity.getHole() != null) {
            dto.setHole(DataTransformer.transform(entity.getHole(), language));
        }

        if (entity.getCoatingTank() != null) {
            dto.setCoatingTank(DataTransformer.transform(entity.getCoatingTank(), language));
        }

        if (entity.getHandle() != null) {
            dto.setHandle(DataTransformer.transform(entity.getHandle(), language));
        }

        dto.setOther(entity.getOther());
        dto.setSicken(entity.getSicken());

        transformCommonFields(dto, entity, language);

        fromEntityToModel(entity, dto);
        return dto;
    }

    /**
     * Transforms ConicalCanister into ConicalCanisterEntity
     * @param dto
     * @return {@link ConicalCanisterEntity}
     */
    public static ConicalCanisterEntity transform(ConicalCanister dto) {
        if (dto == null) {
            return null;
        }

        ConicalCanisterEntity entity = new ConicalCanisterEntity();
        entity.setId(dto.getId());

        // own data
        entity.setOverflowVolume(dto.getOverflowVolume())
                .setUnCode(dto.getUnCode());

        if (dto.getExZone() != null && dto.getExZone().getId() != null) {
            ExZoneEntity exZoneEntity = new ExZoneEntity();
            exZoneEntity.setId(dto.getExZone().getId());
            entity.setExZone(exZoneEntity);
        }

        entity.setPermLock(dto.getPermLock())
                .setEmbossing(dto.getEmbossing())
                .setEmbossingPosition(dto.getEmbossingPosition());

        if (dto.getCoatingInside() != null && dto.getCoatingInside().getId() != null) {
            CoatingEntity coatingEntity = new CoatingEntity();
            coatingEntity.setId(dto.getCoatingInside().getId());
            entity.setCoatingInside(coatingEntity);
        }

        if (dto.getCoatingOutside() != null && dto.getCoatingOutside().getId() != null) {
            CoatingEntity coatingEntity = new CoatingEntity();
            coatingEntity.setId(dto.getCoatingOutside().getId());
            entity.setCoatingOutside(coatingEntity);
        }

        entity.setDrawingNumber(dto.getDrawingNumber());
        entity.setDrawing(dto.getDrawing())
                .setDrawingFilename(dto.getDrawingFilename())
                .setDrawingContentType(dto.getDrawingContentType())
                .setDrawingCAD(dto.getDrawingCAD())
                .setDrawingCADFilename(dto.getDrawingCADFilename())
                .setDrawingCADContentType(dto.getDrawingCADContentType())
                .setManufacturerSpecifications(dto.getManufacturerSpecifications())
                .setManufacturerSpecificationsFilename(dto.getManufacturerSpecificationsFilename())
                .setManufacturerSpecificationsContentType(dto.getManufacturerSpecificationsContentType());

        entity.setOverallHeight(dto.getOverallHeight());
        entity.setOverallHeightMax(dto.getOverallHeightMax());
        entity.setOverallHeightMin(dto.getOverallHeightMin());

        entity.setLidWallThickness(dto.getLidWallThickness());
        entity.setLidWallThicknessMax(dto.getLidWallThicknessMax());
        entity.setLidWallThicknessMin(dto.getLidWallThicknessMin());

        entity.setGroundWallThickness(dto.getGroundWallThickness());
        entity.setGroundWallThicknessMax(dto.getGroundWallThicknessMax());
        entity.setGroundWallThicknessMin(dto.getGroundWallThicknessMin());

        entity.setWallThicknessContainer(dto.getWallThicknessContainer());
        entity.setWallThicknessContainerMax(dto.getWallThicknessContainerMax());
        entity.setWallThicknessContainerMin(dto.getWallThicknessContainerMin());

        entity.setTotalWeight(dto.getTotalWeight())
                .setTotalWeightMax(dto.getTotalWeightMax())
                .setTotalWeightMin(dto.getTotalWeightMin());

        entity.setWidth(dto.getWidth());
        entity.setWidthMax(dto.getWidthMax());
        entity.setWidthMin(dto.getWidthMin());

        entity.setDepth(dto.getDepth());
        entity.setDepthMax(dto.getDepthMax());
        entity.setDepthMin(dto.getDepthMin());

        entity.setSicken(dto.getSicken());

        if (dto.getContainerMaterial() != null && dto.getContainerMaterial().getId() != null) {
            MaterialEntity materialEntity = new MaterialEntity();
            materialEntity.setId(dto.getContainerMaterial().getId());
            entity.setContainerMaterial(materialEntity);
        }

        if (dto.getHole() != null && dto.getHole().getId() != null) {
            ClosureEntity closureEntity = new ClosureEntity();
            closureEntity.setId(dto.getHole().getId());
            entity.setHole(closureEntity);
        }

        if (dto.getHandle() != null && dto.getHandle().getId() != null) {
            HandleEntity handleEntity = new HandleEntity();
            handleEntity.setId(dto.getHandle().getId());
            entity.setHandle(handleEntity);
        }

        entity.setOther(dto.getOther());

        transformCommonFields(entity, dto);

        fromModelToEntity(dto, entity);
        return entity;
    }

    /**
     * Transforms ConicalCanisterEntity into ConicalCanister
     * @param entity
     * @return {@link ConicalCanister}
     */
    public static ConicalCanister transform(ConicalCanisterEntity entity) {
        return transform(entity, null);
    }

    /**
     * Transforms ConicalCanisterEntity into ConicalCanister
     * @param entity
     * @return {@link ConicalCanister}
     */
    public static ConicalCanister transform(ConicalCanisterEntity entity, Language language) {
        if (entity == null) {
            return null;
        }

        ConicalCanister dto = new ConicalCanister();
        dto.setId(entity.getId());

        // own data
        dto.setOverflowVolume(entity.getOverflowVolume());
        dto.setUnCode(entity.getUnCode());

        if (entity.getExZone() != null) {
            dto.setExZone(DataTransformer.transform(entity.getExZone(), language));
        }

        dto.setPermLock(entity.getPermLock());
        dto.setEmbossing(entity.getEmbossing());
        dto.setEmbossingPosition(entity.getEmbossingPosition());

        if (entity.getCoatingInside() != null) {
            dto.setCoatingInside(DataTransformer.transform(entity.getCoatingInside(), language));
        }

        if (entity.getCoatingOutside() != null) {
            dto.setCoatingOutside(DataTransformer.transform(entity.getCoatingOutside(), language));
        }

        if (entity.getHandle() != null) {
            dto.setHandle(DataTransformer.transform(entity.getHandle(), language));
        }

        dto.setDrawingNumber(entity.getDrawingNumber());

        dto.setDrawing(entity.getDrawing());
        dto.setDrawingFilename(entity.getDrawingFilename());
        dto.setDrawingContentType(entity.getDrawingContentType());

        dto.setDrawingCAD(entity.getDrawingCAD());
        dto.setDrawingCADFilename(entity.getDrawingCADFilename());
        dto.setDrawingCADContentType(entity.getDrawingCADContentType());

        dto.setManufacturerSpecifications(entity.getManufacturerSpecifications());
        dto.setManufacturerSpecificationsFilename(entity.getManufacturerSpecificationsFilename());
        dto.setManufacturerSpecificationsContentType(entity.getManufacturerSpecificationsContentType());

        dto.setOverallHeight(entity.getOverallHeight());
        dto.setOverallHeightMax(entity.getOverallHeightMax());
        dto.setOverallHeightMin(entity.getOverallHeightMin());

        dto.setLidWallThickness(entity.getLidWallThickness());
        dto.setLidWallThicknessMax(entity.getLidWallThicknessMax());
        dto.setLidWallThicknessMin(entity.getLidWallThicknessMin());

        dto.setGroundWallThickness(entity.getGroundWallThickness());
        dto.setGroundWallThicknessMax(entity.getGroundWallThicknessMax());
        dto.setGroundWallThicknessMin(entity.getGroundWallThicknessMin());

        dto.setWallThicknessContainer(entity.getWallThicknessContainer());
        dto.setWallThicknessContainerMax(entity.getWallThicknessContainerMax());
        dto.setWallThicknessContainerMin(entity.getWallThicknessContainerMin());

        dto.setTotalWeight(entity.getTotalWeight());
        dto.setTotalWeightMax(entity.getTotalWeightMax());
        dto.setTotalWeightMin(entity.getTotalWeightMin());

        dto.setWidth(entity.getWidth());
        dto.setWidthMax(entity.getWidthMax());
        dto.setWidthMin(entity.getWidthMin());

        dto.setDepth(entity.getDepth());
        dto.setDepthMax(entity.getDepthMax());
        dto.setDepthMin(entity.getDepthMin());

        if (entity.getContainerMaterial() != null) {
            dto.setContainerMaterial(DataTransformer.transform(entity.getContainerMaterial(), language));
        }

        if (entity.getHole() != null) {
            dto.setHole(DataTransformer.transform(entity.getHole(), language));
        }

        dto.setOther(entity.getOther());
        dto.setSicken(entity.getSicken());

        transformCommonFields(dto, entity, language);

        fromEntityToModel(entity, dto);
        return dto;
    }

    /**
     * Transforms ConicalClampingRing into ConicalClampingRingEntity
     * @param dto
     * @return {@link ConicalClampingRingEntity}
     */
    public static ConicalClampingRingEntity transform(ConicalClampingRing dto) {
        if (dto == null) {
            return null;
        }

        ConicalClampingRingEntity entity = new ConicalClampingRingEntity();
        entity.setId(dto.getId());

        // own data
        entity.setOverflowVolume(dto.getOverflowVolume())
                .setUnCode(dto.getUnCode());

        if (dto.getExZone() != null && dto.getExZone().getId() != null) {
            ExZoneEntity exZoneEntity = new ExZoneEntity();
            exZoneEntity.setId(dto.getExZone().getId());
            entity.setExZone(exZoneEntity);
        }

        entity.setPermLock(dto.getPermLock())
                .setEmbossing(dto.getEmbossing())
                .setEmbossingPosition(dto.getEmbossingPosition());

        if (dto.getCoatingInside() != null && dto.getCoatingInside().getId() != null) {
            CoatingEntity coatingEntity = new CoatingEntity();
            coatingEntity.setId(dto.getCoatingInside().getId());
            entity.setCoatingInside(coatingEntity);
        }

        if (dto.getCoatingOutside() != null && dto.getCoatingOutside().getId() != null) {
            CoatingEntity coatingEntity = new CoatingEntity();
            coatingEntity.setId(dto.getCoatingOutside().getId());
            entity.setCoatingOutside(coatingEntity);
        }

        if (dto.getCoatingTank() != null && dto.getCoatingTank().getId() != null) {
            CoatingEntity coatingEntity = new CoatingEntity();
            coatingEntity.setId(dto.getCoatingTank().getId());
            entity.setCoatingTank(coatingEntity);
        }

        entity.setDrawingNumber(dto.getDrawingNumber());
        entity.setDrawing(dto.getDrawing())
                .setDrawingFilename(dto.getDrawingFilename())
                .setDrawingContentType(dto.getDrawingContentType())
                .setDrawingCAD(dto.getDrawingCAD())
                .setDrawingCADFilename(dto.getDrawingCADFilename())
                .setDrawingCADContentType(dto.getDrawingCADContentType())
                .setManufacturerSpecifications(dto.getManufacturerSpecifications())
                .setManufacturerSpecificationsFilename(dto.getManufacturerSpecificationsFilename())
                .setManufacturerSpecificationsContentType(dto.getManufacturerSpecificationsContentType());

        entity.setOverallHeight(dto.getOverallHeight());
        entity.setOverallHeightMax(dto.getOverallHeightMax());
        entity.setOverallHeightMin(dto.getOverallHeightMin());

        entity.setHullHeight(dto.getHullHeight());
        entity.setHullHeightMax(dto.getHullHeightMax());
        entity.setHullHeightMin(dto.getHullHeightMin());

        entity.setInnerDiameterAbove(dto.getInnerDiameterAbove());
        entity.setInnerDiameterAboveMax(dto.getInnerDiameterAboveMax());
        entity.setInnerDiameterAboveMin(dto.getInnerDiameterAboveMin());

        entity.setInnerDiameterBelow(dto.getInnerDiameterBelow());
        entity.setInnerDiameterBelowMax(dto.getInnerDiameterBelowMax());
        entity.setInnerDiameterBelowMin(dto.getInnerDiameterBelowMin());

        entity.setHullWallThickness(dto.getHullWallThickness());
        entity.setHullWallThicknessMax(dto.getHullWallThicknessMax());
        entity.setHullWallThicknessMin(dto.getHullWallThicknessMin());

        entity.setLidWallThickness(dto.getLidWallThickness());
        entity.setLidWallThicknessMax(dto.getLidWallThicknessMax());
        entity.setLidWallThicknessMin(dto.getLidWallThicknessMin());

        entity.setGroundWallThickness(dto.getGroundWallThickness());
        entity.setGroundWallThicknessMax(dto.getGroundWallThicknessMax());
        entity.setGroundWallThicknessMin(dto.getGroundWallThicknessMin());

        entity.setDiameterClampingRing(dto.getDiameterClampingRing());
        entity.setDiameterClampingRingMax(dto.getDiameterClampingRingMax());
        entity.setDiameterClampingRingMin(dto.getDiameterClampingRingMin());

        entity.setWeightInnerContainer(dto.getWeightInnerContainer());
        entity.setWeightInnerContainerMin(dto.getWeightInnerContainerMin());
        entity.setWeightInnerContainerMax(dto.getWeightInnerContainerMax());

        entity.setTotalWeight(dto.getTotalWeight())
                .setTotalWeightMax(dto.getTotalWeightMax())
                .setTotalWeightMin(dto.getTotalWeightMin());

        if (dto.getContainerMaterial() != null && dto.getContainerMaterial().getId() != null) {
            MaterialEntity materialEntity = new MaterialEntity();
            materialEntity.setId(dto.getContainerMaterial().getId());
            entity.setContainerMaterial(materialEntity);
        }

        if (dto.getHole() != null && dto.getHole().getId() != null) {
            ClosureEntity closureEntity = new ClosureEntity();
            closureEntity.setId(dto.getHole().getId());
            entity.setHole(closureEntity);
        }

        if (dto.getHandle() != null && dto.getHandle().getId() != null) {
            HandleEntity handleEntity = new HandleEntity();
            handleEntity.setId(dto.getHandle().getId());
            entity.setHandle(handleEntity);
        }

        entity.setOther(dto.getOther());
        entity.setSicken(dto.getSicken());

        transformCommonFields(entity, dto);

        fromModelToEntity(dto, entity);
        return entity;
    }

    /**
     * Transforms ConicalClampingRingEntity into ConicalClampingRing
     * @param entity
     * @return {@link ConicalClampingRing}
     */
    public static ConicalClampingRing transform(ConicalClampingRingEntity entity) {
        return transform(entity, null);
    }

    /**
     * Transforms ConicalClampingRingEntity into ConicalClampingRing
     * @param entity
     * @return {@link ConicalClampingRing}
     */
    public static ConicalClampingRing transform(ConicalClampingRingEntity entity, Language language) {
        if (entity == null) {
            return null;
        }

        ConicalClampingRing dto = new ConicalClampingRing();
        dto.setId(entity.getId());

        // own data
        dto.setOverflowVolume(entity.getOverflowVolume());
        dto.setUnCode(entity.getUnCode());

        if (entity.getExZone() != null) {
            dto.setExZone(DataTransformer.transform(entity.getExZone(), language));
        }

        dto.setPermLock(entity.getPermLock());
        dto.setEmbossing(entity.getEmbossing());
        dto.setEmbossingPosition(entity.getEmbossingPosition());

        if (entity.getCoatingInside() != null) {
            dto.setCoatingInside(DataTransformer.transform(entity.getCoatingInside(), language));
        }

        if (entity.getCoatingOutside() != null) {
            dto.setCoatingOutside(DataTransformer.transform(entity.getCoatingOutside(), language));
        }

        if (entity.getCoatingTank() != null) {
            dto.setCoatingTank(DataTransformer.transform(entity.getCoatingTank(), language));
        }

        dto.setDrawingNumber(entity.getDrawingNumber());

        dto.setDrawing(entity.getDrawing());
        dto.setDrawingFilename(entity.getDrawingFilename());
        dto.setDrawingContentType(entity.getDrawingContentType());

        dto.setDrawingCAD(entity.getDrawingCAD());
        dto.setDrawingCADFilename(entity.getDrawingCADFilename());
        dto.setDrawingCADContentType(entity.getDrawingCADContentType());

        dto.setManufacturerSpecifications(entity.getManufacturerSpecifications());
        dto.setManufacturerSpecificationsFilename(entity.getManufacturerSpecificationsFilename());
        dto.setManufacturerSpecificationsContentType(entity.getManufacturerSpecificationsContentType());

        dto.setOverallHeight(entity.getOverallHeight());
        dto.setOverallHeightMax(entity.getOverallHeightMax());
        dto.setOverallHeightMin(entity.getOverallHeightMin());

        dto.setHullHeight(entity.getHullHeight());
        dto.setHullHeightMax(entity.getHullHeightMax());
        dto.setHullHeightMin(entity.getHullHeightMin());

        dto.setInnerDiameterAbove(entity.getInnerDiameterAbove());
        dto.setInnerDiameterAboveMax(entity.getInnerDiameterAboveMax());
        dto.setInnerDiameterAboveMin(entity.getInnerDiameterAboveMin());

        dto.setInnerDiameterBelow(entity.getInnerDiameterBelow());
        dto.setInnerDiameterBelowMax(entity.getInnerDiameterBelowMax());
        dto.setInnerDiameterBelowMin(entity.getInnerDiameterBelowMin());

        dto.setHullWallThickness(entity.getHullWallThickness());
        dto.setHullWallThicknessMax(entity.getHullWallThicknessMax());
        dto.setHullWallThicknessMin(entity.getHullWallThicknessMin());

        dto.setLidWallThickness(entity.getLidWallThickness());
        dto.setLidWallThicknessMax(entity.getLidWallThicknessMax());
        dto.setLidWallThicknessMin(entity.getLidWallThicknessMin());

        dto.setGroundWallThickness(entity.getGroundWallThickness());
        dto.setGroundWallThicknessMax(entity.getGroundWallThicknessMax());
        dto.setGroundWallThicknessMin(entity.getGroundWallThicknessMin());

        dto.setTotalWeight(entity.getTotalWeight());
        dto.setTotalWeightMax(entity.getTotalWeightMax());
        dto.setTotalWeightMin(entity.getTotalWeightMin());

        dto.setDiameterClampingRing(entity.getDiameterClampingRing());
        dto.setDiameterClampingRingMax(entity.getDiameterClampingRingMax());
        dto.setDiameterClampingRingMin(entity.getDiameterClampingRingMin());

        dto.setWeightInnerContainer(entity.getWeightInnerContainer());
        dto.setWeightInnerContainerMin(entity.getWeightInnerContainerMin());
        dto.setWeightInnerContainerMax(entity.getWeightInnerContainerMax());

        if (entity.getContainerMaterial() != null) {
            dto.setContainerMaterial(DataTransformer.transform(entity.getContainerMaterial(), language));
        }

        if (entity.getHole() != null) {
            dto.setHole(DataTransformer.transform(entity.getHole(), language));
        }

        if (entity.getHandle() != null) {
            dto.setHandle(DataTransformer.transform(entity.getHandle(), language));
        }


        dto.setOther(entity.getOther());
        dto.setSicken(entity.getSicken());

        transformCommonFields(dto, entity, language);

        fromEntityToModel(entity, dto);
        return dto;
    }

    /**
     * Transforms CylindricalClampingRing into CylindricalClampingRingEntity
     * @param dto
     * @return {@link CylindricalClampingRingEntity}
     */
    public static CylindricalClampingRingEntity transform(CylindricalClampingRing dto) {
        if (dto == null) {
            return null;
        }

        CylindricalClampingRingEntity entity = new CylindricalClampingRingEntity();
        entity.setId(dto.getId());

        // own data
        entity.setOverflowVolume(dto.getOverflowVolume())
                .setUnCode(dto.getUnCode());

        if (dto.getExZone() != null && dto.getExZone().getId() != null) {
            ExZoneEntity exZoneEntity = new ExZoneEntity();
            exZoneEntity.setId(dto.getExZone().getId());
            entity.setExZone(exZoneEntity);
        }

        entity.setPermLock(dto.getPermLock())
                .setEmbossing(dto.getEmbossing())
                .setEmbossingPosition(dto.getEmbossingPosition());

        if (dto.getCoatingInside() != null && dto.getCoatingInside().getId() != null) {
            CoatingEntity coatingEntity = new CoatingEntity();
            coatingEntity.setId(dto.getCoatingInside().getId());
            entity.setCoatingInside(coatingEntity);
        }

        if (dto.getCoatingOutside() != null && dto.getCoatingOutside().getId() != null) {
            CoatingEntity coatingEntity = new CoatingEntity();
            coatingEntity.setId(dto.getCoatingOutside().getId());
            entity.setCoatingOutside(coatingEntity);
        }

        entity.setDrawingNumber(dto.getDrawingNumber());
        entity.setDrawing(dto.getDrawing())
                .setDrawingFilename(dto.getDrawingFilename())
                .setDrawingContentType(dto.getDrawingContentType())
                .setDrawingCAD(dto.getDrawingCAD())
                .setDrawingCADFilename(dto.getDrawingCADFilename())
                .setDrawingCADContentType(dto.getDrawingCADContentType())
                .setManufacturerSpecifications(dto.getManufacturerSpecifications())
                .setManufacturerSpecificationsFilename(dto.getManufacturerSpecificationsFilename())
                .setManufacturerSpecificationsContentType(dto.getManufacturerSpecificationsContentType());


        entity.setOverallHeight(dto.getOverallHeight());
        entity.setOverallHeightMax(dto.getOverallHeightMax());
        entity.setOverallHeightMin(dto.getOverallHeightMin());

        entity.setHullHeight(dto.getHullHeight());
        entity.setHullHeightMax(dto.getHullHeightMax());
        entity.setHullHeightMin(dto.getHullHeightMin());

        entity.setLidWallThickness(dto.getLidWallThickness());
        entity.setLidWallThicknessMax(dto.getLidWallThicknessMax());
        entity.setLidWallThicknessMin(dto.getLidWallThicknessMin());

        entity.setGroundWallThickness(dto.getGroundWallThickness());
        entity.setGroundWallThicknessMax(dto.getGroundWallThicknessMax());
        entity.setGroundWallThicknessMin(dto.getGroundWallThicknessMin());

        entity.setDiameterClampingRing(dto.getDiameterClampingRing());
        entity.setDiameterClampingRingMax(dto.getDiameterClampingRingMax());
        entity.setDiameterClampingRingMin(dto.getDiameterClampingRingMin());

        entity.setInnerDiameter(dto.getInnerDiameter());
        entity.setInnerDiameterMax(dto.getInnerDiameterMax());
        entity.setInnerDiameterMin(dto.getInnerDiameterMin());

        entity.setTotalWeight(dto.getTotalWeight())
                .setTotalWeightMax(dto.getTotalWeightMax())
                .setTotalWeightMin(dto.getTotalWeightMin());

        entity.setWallThicknessContainer(dto.getWallThicknessContainer());
        entity.setWallThicknessContainerMin(dto.getWallThicknessContainerMin());
        entity.setWallThicknessContainerMax(dto.getWallThicknessContainerMax());

        entity.setWeightInnerContainer(dto.getWeightInnerContainer());
        entity.setWeightInnerContainerMin(dto.getWeightInnerContainerMin());
        entity.setWeightInnerContainerMax(dto.getWeightInnerContainerMax());

        if (dto.getContainerMaterial() != null && dto.getContainerMaterial().getId() != null) {
            MaterialEntity materialEntity = new MaterialEntity();
            materialEntity.setId(dto.getContainerMaterial().getId());
            entity.setContainerMaterial(materialEntity);
        }

        if (dto.getHole() != null && dto.getHole().getId() != null) {
            ClosureEntity closureEntity = new ClosureEntity();
            closureEntity.setId(dto.getHole().getId());
            entity.setHole(closureEntity);
        }

        if (dto.getHandle() != null && dto.getHandle().getId() != null) {
            HandleEntity handleEntity = new HandleEntity();
            handleEntity.setId(dto.getHandle().getId());
            entity.setHandle(handleEntity);
        }

        entity.setOther(dto.getOther());
        entity.setSicken(dto.getSicken());

        transformCommonFields(entity, dto);

        fromModelToEntity(dto, entity);
        return entity;
    }

    /**
     * Transforms CylindricalClampingRingEntity into CylindricalClampingRing
     * @param entity
     * @return {@link CylindricalClampingRing}
     */
    public static CylindricalClampingRing transform(CylindricalClampingRingEntity entity) {
        return transform(entity, null);
    }

    /**
     * Transforms CylindricalClampingRingEntity into CylindricalClampingRing
     * @param entity
     * @return {@link CylindricalClampingRing}
     */
    public static CylindricalClampingRing transform(CylindricalClampingRingEntity entity, Language language) {
        if (entity == null) {
            return null;
        }

        CylindricalClampingRing dto = new CylindricalClampingRing();
        dto.setId(entity.getId());

        // own data
        dto.setOverflowVolume(entity.getOverflowVolume());
        dto.setUnCode(entity.getUnCode());

        if (entity.getExZone() != null) {
            dto.setExZone(DataTransformer.transform(entity.getExZone(), language));
        }

        dto.setPermLock(entity.getPermLock());
        dto.setEmbossing(entity.getEmbossing());
        dto.setEmbossingPosition(entity.getEmbossingPosition());

        if (entity.getCoatingInside() != null) {
            dto.setCoatingInside(DataTransformer.transform(entity.getCoatingInside(), language));
        }

        if (entity.getCoatingOutside() != null) {
            dto.setCoatingOutside(DataTransformer.transform(entity.getCoatingOutside(), language));
        }

        if (entity.getHandle() != null) {
            dto.setHandle(DataTransformer.transform(entity.getHandle(), language));
        }

        dto.setDrawingNumber(entity.getDrawingNumber());

        dto.setDrawing(entity.getDrawing());
        dto.setDrawingFilename(entity.getDrawingFilename());
        dto.setDrawingContentType(entity.getDrawingContentType());

        dto.setDrawingCAD(entity.getDrawingCAD());
        dto.setDrawingCADFilename(entity.getDrawingCADFilename());
        dto.setDrawingCADContentType(entity.getDrawingCADContentType());

        dto.setManufacturerSpecifications(entity.getManufacturerSpecifications());
        dto.setManufacturerSpecificationsFilename(entity.getManufacturerSpecificationsFilename());
        dto.setManufacturerSpecificationsContentType(entity.getManufacturerSpecificationsContentType());

        dto.setOverallHeight(entity.getOverallHeight());
        dto.setOverallHeightMax(entity.getOverallHeightMax());
        dto.setOverallHeightMin(entity.getOverallHeightMin());

        dto.setHullHeight(entity.getHullHeight());
        dto.setHullHeightMax(entity.getHullHeightMax());
        dto.setHullHeightMin(entity.getHullHeightMin());

        dto.setLidWallThickness(entity.getLidWallThickness());
        dto.setLidWallThicknessMax(entity.getLidWallThicknessMax());
        dto.setLidWallThicknessMin(entity.getLidWallThicknessMin());

        dto.setGroundWallThickness(entity.getGroundWallThickness());
        dto.setGroundWallThicknessMax(entity.getGroundWallThicknessMax());
        dto.setGroundWallThicknessMin(entity.getGroundWallThicknessMin());

        dto.setTotalWeight(entity.getTotalWeight());
        dto.setTotalWeightMax(entity.getTotalWeightMax());
        dto.setTotalWeightMin(entity.getTotalWeightMin());

        dto.setDiameterClampingRing(entity.getDiameterClampingRing());
        dto.setDiameterClampingRingMax(entity.getDiameterClampingRingMax());
        dto.setDiameterClampingRingMin(entity.getDiameterClampingRingMin());

        dto.setInnerDiameter(entity.getInnerDiameter());
        dto.setInnerDiameterMax(entity.getInnerDiameterMax());
        dto.setInnerDiameterMin(entity.getInnerDiameterMin());

        dto.setWallThicknessContainer(entity.getWallThicknessContainer());
        dto.setWallThicknessContainerMin(entity.getWallThicknessContainerMin());
        dto.setWallThicknessContainerMax(entity.getWallThicknessContainerMax());

        dto.setWeightInnerContainer(entity.getWeightInnerContainer());
        dto.setWeightInnerContainerMin(entity.getWeightInnerContainerMin());
        dto.setWeightInnerContainerMax(entity.getWeightInnerContainerMax());

        if (entity.getContainerMaterial() != null) {
            dto.setContainerMaterial(DataTransformer.transform(entity.getContainerMaterial(), language));
        }

        if (entity.getHole() != null) {
            dto.setHole(DataTransformer.transform(entity.getHole(), language));
        }

        dto.setOther(entity.getOther());
        dto.setSicken(entity.getSicken());

        transformCommonFields(dto, entity, language);

        fromEntityToModel(entity, dto);
        return dto;
    }

    private static void transformCommonFields(ContainerEntity entity, Container dto) {
        // container data
        entity.setSapCode(dto.getSapCode());
        entity.setLocalCode(dto.getLocalCode());
        entity.setGlobalCode(dto.getGlobalCode());
        entity.setStatus(dto.getStatus());

        if (dto.getLocation() != null && dto.getLocation().getId() != null) {
            LocationEntity locationEntity = new LocationEntity();
            locationEntity.setId(dto.getLocation().getId());
            entity.setLocation(locationEntity);
        }

        if (dto.getBusinessUnit() != null && dto.getBusinessUnit().getId() != null) {
            BusinessUnitEntity businessUnit = new BusinessUnitEntity();
            businessUnit.setId(dto.getBusinessUnit().getId());
            entity.setBusinessUnit(businessUnit);
        }

        entity.setNominalVolume(dto.getNominalVolume());
        entity.setDesignation(dto.getDesignation());

        if (dto.getCompany() != null && dto.getCompany().getId() != null) {
            CompanyEntity companyEntity= new CompanyEntity();
            companyEntity.setId(dto.getCompany().getId());
            entity.setCompany(companyEntity);
        }

        if (CollectionHelper.isNotEmpty(dto.getInfoList())) {
            Set<ContainerInfoEntity> infoList = new HashSet<>();
            for (ContainerInfo item : dto.getInfoList()) {
                // if language is disabled continue
                if (item.getLanguage() == null || item.getLanguage().getStatus() != Status.ACTIVE) continue;

                if (StringHelper.isBlank(item.getComments())) {
                    continue;
                }
                ContainerInfoEntity infoEntity = new ContainerInfoEntity();
                infoEntity.setId(item.getId());
                infoEntity.setLanguage(new LanguageEntity(item.getLanguage()));
                infoEntity.setComments(item.getComments());
                infoEntity.setContainer(entity);
                infoList.add(infoEntity);
            }
            if (CollectionHelper.isNotEmpty(infoList)) {
                entity.setInfoList(infoList);
            }
        }

        if (CollectionHelper.isNotEmpty(dto.getPhotos())) {
            Set<ContainerPhotoEntity> photos = new HashSet<>();
            for (ContainerPhoto item : dto.getPhotos()) {
                ContainerPhotoEntity itemEntity = transform(item);
                itemEntity.setContainer(entity);
                photos.add(itemEntity);
            }
            entity.setPhotos(photos);
        }
    }

    private static void transformCommonFields(Container dto, ContainerEntity entity, Language language) {
        // container data
        dto.setSapCode(entity.getSapCode());
        dto.setLocalCode(entity.getLocalCode());
        dto.setGlobalCode(entity.getGlobalCode());
        dto.setStatus(entity.getStatus());

        if (entity.getLocation() != null) {
            dto.setLocation(DataTransformer.transform(entity.getLocation()));
        }

        if (entity.getBusinessUnit() != null) {
            dto.setBusinessUnit(DataTransformer.transform(entity.getBusinessUnit(), language));
        }

        if (entity.getCompany() != null) {
            dto.setCompany(DataTransformer.transform(entity.getCompany()));
        }

        dto.setNominalVolume(entity.getNominalVolume());
        dto.setDesignation(entity.getDesignation());

        if (CollectionHelper.isNotEmpty(entity.getInfoList())) {
            List<ContainerInfo> infoList = new ArrayList<>();
            for (ContainerInfoEntity item : entity.getInfoList()) {
                // if language is disabled continue
                if (item.getLanguage() == null || item.getLanguage().getStatus() != Status.ACTIVE) continue;

                ContainerInfo info = new ContainerInfo();
                info.setId(item.getId());
                info.setLanguage(Language.valueOf(item.getLanguage().getId()));
                info.setComments(item.getComments());
                infoList.add(info);
            }
            dto.setInfoList(infoList);
        }

        if (CollectionHelper.isNotEmpty(entity.getPhotos())) {
            List<ContainerPhoto> photos = new ArrayList<>();
            for (ContainerPhotoEntity itemEntity : entity.getPhotos()) {
                photos.add(transform(itemEntity));
            }
            dto.setPhotos(photos);
        }
    }

    public static ContainerPhotoEntity transform(ContainerPhoto dto) {
        ContainerPhotoEntity entity = new ContainerPhotoEntity();
        entity.setId(dto.getId());
        entity.setData(dto.getData());
        entity.setFilename(dto.getFilename());
        entity.setContentType(dto.getContentType());
        return entity;
    }

    public static ContainerPhoto transform(ContainerPhotoEntity entity) {
        ContainerPhoto dto = new ContainerPhoto();
        dto.setId(entity.getId());
        dto.setData(entity.getData());
        dto.setFilename(entity.getFilename());
        dto.setContentType(entity.getContentType());
        return dto;
    }
}
