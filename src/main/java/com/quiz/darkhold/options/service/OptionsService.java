package com.quiz.darkhold.options.service;

import com.quiz.darkhold.challenge.entity.Challenge;
import com.quiz.darkhold.challenge.repository.ChallengeRepository;
import com.quiz.darkhold.login.repository.UserRepository;
import com.quiz.darkhold.options.model.ChallengeInfo;
import com.quiz.darkhold.options.model.ChallengeSummary;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

@Service
public class OptionsService {
    private final Log log = LogFactory.getLog(OptionsService.class);

    private final ChallengeRepository challengeRepository;

    private final UserRepository userRepository;

    public OptionsService(final ChallengeRepository challengeRepository, final UserRepository userRepository) {
        this.challengeRepository = challengeRepository;
        this.userRepository = userRepository;
    }

    /**
     * get all challenges and display it there.
     *
     * @return challenges
     */
    public ChallengeInfo populateChallengeInfo(final String email) {
        var user = userRepository.findByEmail(email);
        log.info(user.getId());
        var challenges = challengeRepository.findByChallengeOwner(user.getId());
        log.info("total challenges owned by the user is " + challenges.size());
        var summaries = challenges.stream().map(this::getChallengeSummary).toList();
        var challengeInfo = new ChallengeInfo();
        challengeInfo.setChallengeSummaryList(summaries);
        return challengeInfo;
    }

    private ChallengeSummary getChallengeSummary(final Challenge challenge) {
        var summary = new ChallengeSummary();
        summary.setChallengeId(challenge.getId().intValue());
        summary.setChallengeName(challenge.getTitle());
        return summary;
    }
}
