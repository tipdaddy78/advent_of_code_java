package com.tipdaddy.adventofcode.year_2022;

import com.tipdaddy.adventofcode.util.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day2 {

    private enum MatchResult {
        WIN,
        LOSS,
        DRAW
    }

    private enum Move {
        ROCK,
        PAPER,
        SCISSORS
    }

    private static final Map<String, Move> MOVE_STRATEGY_MAP = new HashMap<>();
    private static final Map<String, MatchResult> MATCH_RESULT_STRATEGY_MAP = new HashMap<>();

    static {
        MOVE_STRATEGY_MAP.put("A", Move.ROCK);
        MOVE_STRATEGY_MAP.put("B", Move.PAPER);
        MOVE_STRATEGY_MAP.put("C", Move.SCISSORS);
        MOVE_STRATEGY_MAP.put("X", Move.ROCK);
        MOVE_STRATEGY_MAP.put("Y", Move.PAPER);
        MOVE_STRATEGY_MAP.put("Z", Move.SCISSORS);

        MATCH_RESULT_STRATEGY_MAP.put("X", MatchResult.LOSS);
        MATCH_RESULT_STRATEGY_MAP.put("Y", MatchResult.DRAW);
        MATCH_RESULT_STRATEGY_MAP.put("Z", MatchResult.WIN);
    }

    public static int part1_getRoundScore(final String yourEncryptedMove, final String opponentEncryptedMove) {

        final Move opponentMove = MOVE_STRATEGY_MAP.get(opponentEncryptedMove);
        final Move yourMove = MOVE_STRATEGY_MAP.get(yourEncryptedMove);

        MatchResult matchResult = null;
        if (opponentMove.equals(yourMove)) {
            matchResult = MatchResult.DRAW;
        } else if (opponentMove.equals(Move.ROCK)) {
            if (yourMove.equals(Move.PAPER)) {
                matchResult = MatchResult.WIN;
            } else {
                matchResult = MatchResult.LOSS;
            }
        } else if (opponentMove.equals(Move.PAPER)) {
            if (yourMove.equals(Move.SCISSORS)) {
                matchResult = MatchResult.WIN;
            } else {
                matchResult = MatchResult.LOSS;
            }
        } else if (opponentMove.equals(Move.SCISSORS)) {
            if (yourMove.equals(Move.ROCK)) {
                matchResult = MatchResult.WIN;
            } else {
                matchResult = MatchResult.LOSS;
            }
        }

        return getScore(yourMove, matchResult);
    }

    public static int part2_getRoundScore(final String encryptedMatchResult, final String opponentEncryptedMove) {

        final Move opponentMove = MOVE_STRATEGY_MAP.get(opponentEncryptedMove);
        final MatchResult matchResult = MATCH_RESULT_STRATEGY_MAP.get(encryptedMatchResult);
        final Move yourMove = switch(matchResult) {
            case DRAW: {
                yield opponentMove;
            }
            case WIN: {
                if (opponentMove.equals(Move.ROCK)) {
                    yield Move.PAPER;
                } else if (opponentMove.equals(Move.PAPER)) {
                    yield Move.SCISSORS;
                } else {
                    yield Move.ROCK;
                }
            }
            case LOSS: {
                if (opponentMove.equals(Move.ROCK)) {
                    yield Move.SCISSORS;
                } else if (opponentMove.equals(Move.PAPER)) {
                    yield Move.ROCK;
                } else {
                    yield Move.PAPER;
                }
            }
        };

        return getScore(yourMove, matchResult);
    }

    public static int getScore(final Move yourMove, final MatchResult matchResult) {

        final int shapeScore = switch(yourMove) {
            case ROCK -> 1;
            case PAPER -> 2;
            case SCISSORS -> 3;
        };

        assert matchResult != null;
        final int matchResultScore = switch(matchResult) {
            case WIN -> 6;
            case LOSS -> 0;
            case DRAW -> 3;
        };

        return shapeScore + matchResultScore;
    }

    public static void main(String[] args) {

        List<String> matches = FileReader.readMultiLineFile("2022", "2");
        final int totalScorePart1 = matches
                .stream()
                .mapToInt(line -> {
                    final String[] moves = line.split(" ");
                    return part1_getRoundScore(moves[1], moves[0]);
                }).sum();

        final int totalScorePart2 = matches
                .stream()
                .mapToInt(line -> {
                    final String[] moves = line.split(" ");
                    return part2_getRoundScore(moves[1], moves[0]);
                }).sum();
        System.out.printf("Score for part 1 is %d%n", totalScorePart1);
        System.out.printf("Score for part 2 is %d%n", totalScorePart2);
    }
}
