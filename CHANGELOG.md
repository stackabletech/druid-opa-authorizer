# Changelog

## [Unreleased]

### Changed

- Added support for Druid 30.0.0 and removed support for Druid 27.0.0 ([#104]).
- Removed support for Druid 28.0.1 ([#105]).
- Update dependencies ([#106]).
- Updated Maven plugin versions, added support for Druid 31.0.1 and 30.0.1, removed support for Druid 26.0.0 ([#110]).

[#104]: https://github.com/stackabletech/druid-opa-authorizer/pull/104
[#105]: https://github.com/stackabletech/druid-opa-authorizer/pull/105
[#106]: https://github.com/stackabletech/druid-opa-authorizer/pull/106
[#110]: https://github.com/stackabletech/druid-opa-authorizer/pull/110

## [0.6.0] - 2024-03-19

### Changed

- BREAKING: Add authenticationResult to OPA input ([#85]).

[#85]: https://github.com/stackabletech/druid-opa-authorizer/pull/85

## [0.5.0] - 2023-05-30

### Added

- Added support for Druid `26.0.0` ([#75]).

### Changed

- Changed build system to Maven ([#61]).
- Changed module prefix from `de` to `tech` ([#61]).

### Fixed

- Ignore additional JSON fields the OPA server is sending. This can e.g. be the cause when OPA decision logs are enabled ([#74]).

[#61]: https://github.com/stackabletech/druid-opa-authorizer/pull/61
[#74]: https://github.com/stackabletech/druid-opa-authorizer/pull/74
[#75]: https://github.com/stackabletech/druid-opa-authorizer/pull/75

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
