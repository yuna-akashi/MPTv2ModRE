package mptv2re.util.meta;

public enum MPTv2BlockFlag{
    railgunTurret,
    unitLinkedBlock;

    public final static MPTv2BlockFlag[] all = values();

    /** Values for logic only. Filters out some internal flags. */
    public final static MPTv2BlockFlag[] allLogic = {railgunTurret, unitLinkedBlock};
}
