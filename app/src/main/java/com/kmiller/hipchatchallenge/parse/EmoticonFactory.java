package com.kmiller.hipchatchallenge.parse;

import android.support.annotation.DrawableRes;

import com.kmiller.hipchatchallenge.R;
import com.kmiller.hipchatchallenge.model.EmoticonToken;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Contains a map of all of the EmoticonTokens so a drawable resource can be quickly found
 */
public class EmoticonFactory {

    public static final int NOT_FOUND  = -1;

    //maps tokens to resource id's
    private Map<String, Integer> mTokenMap;

    private final List<EmoticonToken> mEmoticonTokens = Arrays.asList(new EmoticonToken("allthethings", R.drawable.allthethings),
            new EmoticonToken("android", R.drawable.android),
            new EmoticonToken("areyoukiddingme", R.drawable.areyoukiddingme),
            new EmoticonToken("arrington", R.drawable.mike),
            new EmoticonToken("ashton", R.drawable.ashton),
            new EmoticonToken("atlassian", R.drawable.atlassian),
            new EmoticonToken("awthanks", R.drawable.awthanks),
            new EmoticonToken("awyeah", R.drawable.awyea),
            new EmoticonToken("badass", R.drawable.badass),
            new EmoticonToken("badjokeeel", R.drawable.badjokeeel_1375463017),
            new EmoticonToken("badpokerface", R.drawable.badpokerface),
            new EmoticonToken("basket", R.drawable.basket),
            new EmoticonToken("beer", R.drawable.beer),
            new EmoticonToken("bitbucket", R.drawable.bitbucket_1349806629),
            new EmoticonToken("branch", R.drawable.branch_1372171579),
            new EmoticonToken("bumble", R.drawable.bumble),
            new EmoticonToken("bunny", R.drawable.bunny),
            new EmoticonToken("cadbury", R.drawable.cadbury),
            new EmoticonToken("cake", R.drawable.cake),
            new EmoticonToken("camel", R.drawable.camel),
            new EmoticonToken("candycorn", R.drawable.candycorn),
            new EmoticonToken("caruso", R.drawable.caruso),
            new EmoticonToken("ceilingcat", R.drawable.ceilingcat),
            new EmoticonToken("cereal", R.drawable.cerealguy),
            new EmoticonToken("cerealspit", R.drawable.cerealspit_1349463282),
            new EmoticonToken("challengeaccepted", R.drawable.challengeaccepted),
            new EmoticonToken("chewie", R.drawable.chewy),
            new EmoticonToken("chocobunny", R.drawable.chocobunny),
            new EmoticonToken("chris", R.drawable.chris),
            new EmoticonToken("chucknorris", R.drawable.chucknorris_1342735388),
            new EmoticonToken("clarence", R.drawable.clarence_1369414730),
            new EmoticonToken("coffee", R.drawable.coffee),
            new EmoticonToken("confluence", R.drawable.confluence_1367948832),
            new EmoticonToken("content", R.drawable.content),
            new EmoticonToken("continue", R.drawable.continue_1369948567),
            new EmoticonToken("cornelius", R.drawable.cornelius),
            new EmoticonToken("derp", R.drawable.derp),
            new EmoticonToken("disapproval", R.drawable.disapproval),
            new EmoticonToken("dosequis", R.drawable.dosequis),
            new EmoticonToken("drevil", R.drawable.drevil),
            new EmoticonToken("ducreux", R.drawable.ducreux),
            new EmoticonToken("dumb", R.drawable.dumbbitch),
            new EmoticonToken("embarrassed", R.drawable.embarrassed),
            new EmoticonToken("facepalm", R.drawable.facepalm),
            new EmoticonToken("failed", R.drawable.failed_1369948578),
            new EmoticonToken("fap", R.drawable.fap),
            new EmoticonToken("firstworldproblem", R.drawable.firstworldproblem_1342647915),
            new EmoticonToken("foreveralone", R.drawable.foreveralone),
            new EmoticonToken("freddie", R.drawable.freddie),
            new EmoticonToken("fry", R.drawable.fry),
            new EmoticonToken("fuckyeah", R.drawable.fuckyeah),
            new EmoticonToken("fwp", R.drawable.fwp_1342647892),
            new EmoticonToken("garret", R.drawable.garret),
            new EmoticonToken("gates", R.drawable.gates),
            new EmoticonToken("ghost", R.drawable.ghost),
            new EmoticonToken("goodnews", R.drawable.farnsworth),
            new EmoticonToken("greenbeer", R.drawable.greenbeer),
            new EmoticonToken("grumpycat", R.drawable.grumpycat_1366734534),
            new EmoticonToken("gtfo", R.drawable.gtfo),
            new EmoticonToken("haveaseat", R.drawable.haveaseat),
            new EmoticonToken("heart", R.drawable.heart),
            new EmoticonToken("hipchat", R.drawable.hipchat),
            new EmoticonToken("hipster", R.drawable.hipster),
            new EmoticonToken("ilied", R.drawable.ilied),
            new EmoticonToken("indeed", R.drawable.indeed),
            new EmoticonToken("iseewhatyoudidthere", R.drawable.iseewhatyoudidthere_1348262983),
            new EmoticonToken("itsatrap", R.drawable.itsatrap),
            new EmoticonToken("jackie", R.drawable.jackie),
            new EmoticonToken("jira", R.drawable.jira_1350074257),
            new EmoticonToken("jobs", R.drawable.jobs),
            new EmoticonToken("kennypowers", R.drawable.kennypowers),
            new EmoticonToken("kwanzaa", R.drawable.kwanzaa),
            new EmoticonToken("lincoln", R.drawable.lincoln),
            new EmoticonToken("lol", R.drawable.lol),
            new EmoticonToken("lolwut", R.drawable.lolwut),
            new EmoticonToken("megusta", R.drawable.megusta),
            new EmoticonToken("menorah", R.drawable.menorah),
            new EmoticonToken("ninja", R.drawable.ninja),
            new EmoticonToken("notbad", R.drawable.notbad),
            new EmoticonToken("nothingtodohere", R.drawable.nothingtodohere),
            new EmoticonToken("notsureif", R.drawable.notsureif_1342015652),
            new EmoticonToken("notsureifgusta", R.drawable.notsureifgusta_1346093311),
            new EmoticonToken("obama", R.drawable.obama_1352231289),
            new EmoticonToken("ohcrap", R.drawable.ohcrap),
            new EmoticonToken("ohgodwhy", R.drawable.ohgodwhy),
            new EmoticonToken("okay", R.drawable.okay),
            new EmoticonToken("omg", R.drawable.omg),
            new EmoticonToken("oops", R.drawable.oops),
            new EmoticonToken("orly", R.drawable.orly),
            new EmoticonToken("pbr", R.drawable.pbr),
            new EmoticonToken("pete", R.drawable.pete),
            new EmoticonToken("philosoraptor", R.drawable.philosoraptor),
            new EmoticonToken("pingpong", R.drawable.pingpong),
            new EmoticonToken("pokerface", R.drawable.pokerface),
            new EmoticonToken("poo", R.drawable.poo),
            new EmoticonToken("present", R.drawable.present),
            new EmoticonToken("pumpkin", R.drawable.pumpkin),
            new EmoticonToken("rageguy", R.drawable.rageguy),
            new EmoticonToken("rebeccablack", R.drawable.rebeccablack),
            new EmoticonToken("reddit", R.drawable.reddit),
            new EmoticonToken("romney", R.drawable.romney_1352231355),
            new EmoticonToken("rudolph", R.drawable.rudolph),
            new EmoticonToken("sadpanda", R.drawable.sadpanda),
            new EmoticonToken("sadtroll", R.drawable.sadtroll),
            new EmoticonToken("samuel", R.drawable.samuel),
            new EmoticonToken("santa", R.drawable.santa),
            new EmoticonToken("scumbag", R.drawable.scumbag),
            new EmoticonToken("seomoz", R.drawable.seomoz),
            new EmoticonToken("shamrock", R.drawable.shamrock),
            new EmoticonToken("skyrim", R.drawable.skyrim),
            new EmoticonToken("stare", R.drawable.stare),
            new EmoticonToken("success", R.drawable.success_1348262443),
            new EmoticonToken("successful", R.drawable.successful_1369948592),
            new EmoticonToken("sweetjesus", R.drawable.sweetjesus),
            new EmoticonToken("taft", R.drawable.taft),
            new EmoticonToken("tea", R.drawable.tea),
            new EmoticonToken("thumbsdown", R.drawable.thumbs_down),
            new EmoticonToken("thumbsup", R.drawable.thumbs_up),
            new EmoticonToken("tree", R.drawable.tree),
            new EmoticonToken("troll", R.drawable.troll),
            new EmoticonToken("truestory", R.drawable.truestory),
            new EmoticonToken("trump", R.drawable.trump_1352738237),
            new EmoticonToken("turkey", R.drawable.turkey),
            new EmoticonToken("twss", R.drawable.twss_1348262400),
            new EmoticonToken("unknown", R.drawable.unknown_1369948620),
            new EmoticonToken("washington", R.drawable.washington),
            new EmoticonToken("wat", R.drawable.wat),
            new EmoticonToken("wtf", R.drawable.wtf),
            new EmoticonToken("yey", R.drawable.yey),
            new EmoticonToken("yodawg", R.drawable.yodawg),
            new EmoticonToken("yuno", R.drawable.yuno),
            new EmoticonToken("zoidberg", R.drawable.zoidberg),
            new EmoticonToken("kyle", R.drawable.kyle),
            new EmoticonToken("abgb", R.drawable.abgb),
            new EmoticonToken("ezt", R.drawable.ezt),
            new EmoticonToken("fonzie", R.drawable.fonzie),
            new EmoticonToken("8)", R.drawable.cool),
            new EmoticonToken(":#", R.drawable.footinmouth),
            new EmoticonToken(":$", R.drawable.moneymouth),
            new EmoticonToken(":'(", R.drawable.cry),
            new EmoticonToken(":(", R.drawable.frown),
            new EmoticonToken(":)", R.drawable.smile),
            new EmoticonToken(":-*", R.drawable.kiss),
            new EmoticonToken(":D", R.drawable.bigsmile),
            new EmoticonToken(":Z", R.drawable.sealed),
            new EmoticonToken(":\\", R.drawable.slant),
            new EmoticonToken(":o", R.drawable.gasp),
            new EmoticonToken(":p", R.drawable.tongue),
            new EmoticonToken(":|", R.drawable.straightface),
            new EmoticonToken(";)", R.drawable.wink),
            new EmoticonToken(">:-(", R.drawable.angry),
            new EmoticonToken("O:)", R.drawable.innocent));

    public EmoticonFactory() {
        buildTokenMap();
    }

    public List<EmoticonToken> getEmoticonTokens() {
        return mEmoticonTokens;
    }

    public @DrawableRes int getEmoticonResource(String token) {
        if (mTokenMap.containsKey(token)) {
            return mTokenMap.get(token);
        }
        return NOT_FOUND;
    }

    private void buildTokenMap() {
        mTokenMap = new HashMap<>();
        Iterator<EmoticonToken> iterator = mEmoticonTokens.iterator();
        while (iterator.hasNext()) {
            EmoticonToken emoticon = iterator.next();
            mTokenMap.put(emoticon.getToken().toLowerCase(), emoticon.getResource());
        }
    }
}
