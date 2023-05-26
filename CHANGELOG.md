# Changelog

## [Unreleased]

### Fixed

- Ignore additional JSON fields the OPA server is sending. This can e.g. be the cause when OPA decision logs are enabled ([#74]).

### Changed

- Changed build system to Maven ([#61]).
- Changed module prefix from `de` to `tech` ([#61]).

[#61]: https://github.com/stackabletech/druid-opa-authorizer/pull/61
[#74]: https://github.com/stackabletech/druid-opa-authorizer/pull/74

## [0.3.0] - 2022-10-13

### Changed

- Changed Druid dependencies to 24.0.0 ([#57]).

[#57]: https://github.com/stackabletech/druid-opa-authorizer/pull/57

## [0.2.0] - 2022-03-22

### Changed

- Changed from Java 8 to Java 11 support ([#43]).
- Changed HTTP client to Java native ([#43]).

[#43]: https://github.com/stackabletech/druid-opa-authorizer/pull/43

## [0.1.0] - 2022-03-22

Initial Version
